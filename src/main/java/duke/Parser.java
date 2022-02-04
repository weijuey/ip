package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;


/**
 * Class for parsing user input to find command word
 * and arguments, if any, to create Command objects.
 */
public class Parser {
    private DateTimeFormatter dateTimeParser;

    public Parser() {
        dateTimeParser = DateTimeFormatter.ofPattern("HHmm ddMMyyyy");
    }

    /**
     * Checks if the description given by user contains
     * at least one alphabet, which is the benchmark for
     * a valid input.
     *
     * @param d description given by user
     * @return true if description is valid, false otherwise
     */
    private static boolean isValidDescriptor(String d) {
        boolean isValid = false;
        int len = d.length();
        int i = 0;
        while (!isValid && i < len) {
            char c = d.charAt(i);
            isValid = (c <= 122 && c >= 97) || (c <= 90 && c >= 65);
            i++;
        }
        return isValid;
    }

    /**
     * Parses the given user input, finds the command word and
     * arguments if any, and returns the matching Command
     * @param line user input line
     * @return Command object representing command invoked
     * @throws CommandParseException if the line cannot be resolved to a command
     */
    public Command parse(String line) throws CommandParseException {
        int argWhitespace = line.indexOf(' ');
        if (argWhitespace == -1) {
            switch (line.toLowerCase()) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            default:
                throw new CommandParseException("Cannot recognise command", line);
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
                        if (isValidDescriptor(des)) {
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
                int s = line.indexOf('/');
                if (s == -1) {
                    throw new CommandParseException("I need to know the deadline.", line);
                } else {
                    String des = line.substring(argWhitespace + 1, s);
                    String da = line.substring(s + 1);
                    try {
                        if (isValidDescriptor(des)) {
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
                if (isValidDescriptor(d)) {
                    return new TodoCommand(d);
                } else {
                    throw new CommandParseException("Please give a valid description.",
                            line);
                }
            case "find":
                String searching = line.substring(argWhitespace + 1);
                return new FindCommand(searching);
            default:
                throw new CommandParseException("Cannot recognise command", line);
            }
        }
    }
}
