import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private DateTimeFormatter dateTimeParser;

    public Parser() {
        dateTimeParser = DateTimeFormatter.ofPattern("HHmm ddMMyyyy");
    }

    private static boolean validDescriptor(String d) {
        boolean valid = false;
        int len = d.length();
        int i = 0;
        while (!valid && i < len) {
            char c = d.charAt(i);
            valid = (c <= 122 && c >= 97) || (c <= 90 && c >= 65);
        }
        return valid;
    }

    public void parse(String line, TaskList lst, Storage saved, Ui ui) {
        int argWs = line.indexOf(' ');
        if (argWs == -1) {
            switch (line.toLowerCase()) {
                case "bye":
                    ui.printFareWellMessage();
                    System.exit(0);
                case "list":
                    ui.print(lst.toString());
                    break;
                default:
                    System.out.println("Sorry, you did not give a valid command.");
            }
        } else {
            String c = line.substring(0, argWs).toLowerCase();
            switch (c) {
                case "mark":
                    try {
                        int taskIndex = Integer.parseInt(line.substring(argWs + 1)) - 1;
                        if (taskIndex < lst.length()) {
                            if (lst.mark(taskIndex)) {
                                saved.toggleMark(taskIndex);
                                ui.print("Marked done:\n" + lst.get(taskIndex) + "\n");
                            } else {
                                ui.print("You've already done that.");
                            }
                        } else {
                            ui.print("You only have " + taskIndex + " tasks!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You did not provide a number!");
                    }
                    break;
                case "unmark":
                    try {
                        int taskIndex = Integer.parseInt(line.substring(argWs + 1)) - 1;
                        if (taskIndex < lst.length()) {
                            if (lst.unmark(taskIndex)) {
                                saved.toggleMark(taskIndex);
                                ui.print("Oops! Marked undone:\n" + lst.get(taskIndex) + "\n");
                            } else {
                                ui.print("That's not done yet.");
                            }
                        } else {
                            ui.print("You only have " + taskIndex + " tasks!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You did not provide a number!");
                    }
                    break;
                case "delete":
                    try {
                        int taskIndex = Integer.parseInt(line.substring(argWs + 1)) - 1;
                        if (taskIndex < lst.length()) {
                            Task toDelete = lst.get(taskIndex);
                            lst.deleteTask(taskIndex);
                            saved.deleteTask(taskIndex);
                            ui.print("Great, we got this out of the way.\n" + toDelete.toString()
                                    + "\n");
                        } else {
                            ui.print("You only have " + taskIndex + " tasks!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You did not provide a number!");
                    }
                    break;
                case "event":
                    int s = line.indexOf('/');
                    if (s == -1) {
                        System.out.println("I need to know the duration of the event.");
                    } else {
                        String des = line.substring(argWs + 1, s);
                        String da = line.substring(s + 1);
                        try {
                            if (validDescriptor(des)) {
                                lst.addTask(new Event(des, LocalDateTime.parse(da, dateTimeParser)));
                                saved.addTask("E0" + des + "|" + da + "\n");
                                ui.print("How nice, you have something to attend.\n" +
                                        lst.get(lst.length() - 1).toString() + "\n");
                            } else {
                                System.out.println("Please give a valid description.");
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("The date provided cannot be recognised!");
                        }
                    }
                    break;
                case "deadline":
                    int slash = line.indexOf('/');
                    if (slash == -1) {
                        System.out.println("I need to know the deadline.");
                    } else {
                        String des = line.substring(argWs + 1, slash);
                        String da = line.substring(slash + 1);
                        try {
                            if (validDescriptor(des)) {
                                lst.addTask(new Deadline(des, LocalDateTime.parse(da, dateTimeParser)));
                                saved.addTask("D0" + des + "|" + da + "\n");
                                ui.print("That looks urgent.\n" +
                                        lst.get(lst.length() - 1).toString() + "\n");
                            } else {
                                System.out.println("Please give a valid description.");
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("The date provided cannot be recognised!");
                        }
                    }
                    break;
                case "todo":
                    String d = line.substring(argWs + 1);
                    if (validDescriptor(d)) {
                        lst.addTask(new ToDo(d));
                        saved.addTask("T0" + d + "|" + "\n");
                        ui.print("You better do that.\n" +
                                lst.get(lst.length() - 1).toString() + "\n");
                    } else {
                        System.out.println("Please give a valid description.");
                    }
                    break;
                default:
                    System.out.println("Sorry, you did not give a valid command.");
            }
        }
    }
}
