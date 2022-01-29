package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for parsing user input and deciphering the
 * given command.
 */
public class Parser {
    private DateTimeFormatter dateTimeParser;

    public Parser() {
        dateTimeParser = DateTimeFormatter.ofPattern("HHmm ddMMyyyy");
    }

    /**
     * Checks if the given string contains an alphabet,
     * which is the benchmark for a valid description.
     *
     * @param d description of a task given by user
     * @return true if d contains an alphabet, false otherwise
     */
    private static boolean validateDescriptor(String d) {
        boolean isValid = false;
        int len = d.length();
        int i = 0;
        while (!isValid && i < len) {
            char c = d.charAt(i);
            isValid = (c <= 122 && c >= 97) || (c <= 90 && c >= 65);
        }
        return isValid;
    }

    /**
     * Deciphers the given String, which is user input, and
     * returns a Command object representing the Duke command
     * invoked.
     *
     * @param line user input
     * @return command invoked by user
     * @throws CommandParseException if user input cannot be deciphered to a command
     */
    public Command parse(String line) throws CommandParseException {
        int argWhitespace = line.indexOf(' ');
        if (argWhitespace == -1) {
            switch (line.toLowerCase()) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
            }
        } else {
            String c = line.substring(0, argWhitespace).toLowerCase();
            switch (c) {
            case "mark":
                try {
                    int taskIndex = Integer.parseInt(line.substring(argWhitespace + 1)) - 1;
                    return new MarkCommand(taskIndex);
                } catch (NumberFormatException e) {
                    throw new CommandParseException("You did not provide a number!", line);
                }
            case "unmark":
                try {
                    int taskIndex = Integer.parseInt(line.substring(argWhitespace + 1)) - 1;
                    return new UnmarkCommand(taskIndex);
                } catch (NumberFormatException e) {
                    throw new CommandParseException("You did not provide a number!", line);
                }
            case "delete":
                try {
                    int taskIndex = Integer.parseInt(line.substring(argWhitespace + 1)) - 1;
                    return new DeleteCommand(taskIndex);
                } catch (NumberFormatException e) {
                    throw new CommandParseException("You did not provide a number!", line);
                }
            case "event":
                int slash = line.indexOf('/');
                if (slash == -1) {
                    throw new CommandParseException("I need to know the duration of the event.",
                            line);
                } else {
                    String des = line.substring(argWhitespace + 1, slash);
                    String da = line.substring(slash + 1);
                    try {
                        if (validateDescriptor(des)) {
                            return new EventCommand(des, LocalDateTime.parse(da, dateTimeParser));
                        } else {
                            throw new CommandParseException("Please give a valid description.",
                                    line);
                        }
                    } catch (DateTimeParseException e) {
                        throw new CommandParseException("The date provided cannot be recognised!",
                                line);
                    }
                }
            case "deadline":
                int s= line.indexOf('/');
                if (s == -1) {
                    System.out.println("I need to know the deadline.");
                } else {
                    String des = line.substring(argWhitespace + 1, s);
                    String da = line.substring(s + 1);
                    try {
                        if (validateDescriptor(des)) {
                            return new DeadlineCommand(des, LocalDateTime.parse(da, dateTimeParser));
                        } else {
                            throw new CommandParseException("Please give a valid description.",
                                    line);
                        }
                    } catch (DateTimeParseException e) {
                        throw new CommandParseException("The date provided cannot be recognised!",
                                line);
                    }
                }
            case "todo":
                String d = line.substring(argWhitespace + 1);
                if (validateDescriptor(d)) {
                    return new ToDoCommand(d);
                } else {
                    throw new CommandParseException("Please give a valid description.",
                            line);
                }
            }
        }
        throw new CommandParseException("Cannot recognise command", line);
    }
}
