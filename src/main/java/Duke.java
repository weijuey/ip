import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Duke {
    private static String welcomeMessage = "Hi";

    private static String farewellMessage = "Bye";

    private static DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("ddMMyyyy");

    private static ArrayList<Task> storedTasks;

    private static int storedTaskCount;

    private static boolean validDescriptor(String d) {
        boolean valid = false;
        int len = d.length();
        int i = 0;
        while (!valid && i < len) {
            char c = d.charAt(i);
            valid = valid || (c <= 122 && c >= 97) || (c <= 90 && c >= 65);
        }
        return valid;
    }

    public static void main(String[] args) throws IOException {
        storedTasks = new ArrayList<>();
        storedTaskCount = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(welcomeMessage);

        while (true) {
            String nl = input.readLine();
            int t = nl.indexOf(' ');
            if (t == -1) {
                switch (nl.toLowerCase()) {
                    case "bye":
                        System.out.println(farewellMessage);
                        System.exit(0);
                    case "list":
                        for (int i = 1; i <= storedTaskCount; i++) {
                            System.out.println(i + ": " + storedTasks.get(i - 1));
                        }
                        break;
                    default:
                        System.out.println("Sorry, you did not give a valid command.");
                }
            } else {
                String c = nl.substring(0, t).toLowerCase();
                switch (c) {
                    case "mark":
                        try {
                            int task = Integer.parseInt(nl.substring(t + 1)) - 1;
                            if (task < storedTaskCount) {
                                storedTasks.get(task).markDone();
                                System.out.printf("Marked done:%n%s%n"
                                        , storedTasks.get(task).toString());
                            } else {
                                System.out.printf("You only have %d tasks!%n",
                                        storedTaskCount);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("You did not provide a number!");
                        }
                        break;
                    case "unmark":
                        try {
                            int task = Integer.parseInt(nl.substring(t + 1)) - 1;
                            if (task < storedTaskCount) {
                                storedTasks.get(task).markUndone();
                                System.out.printf("Oops! Marked undone:%n%s%n"
                                        , storedTasks.get(task).toString());
                            } else {
                                System.out.printf("You only have %d tasks!%n",
                                        storedTaskCount);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("You did not provide a number!");
                        }
                        break;
                    case "delete":
                        try {
                            int task = Integer.parseInt(nl.substring(t + 1)) - 1;
                            if (task < storedTaskCount) {
                                System.out.printf("Great, we got this out of the way.%n%s%n"
                                        , storedTasks.get(task).toString());
                                storedTasks.remove(task);
                                storedTaskCount--;
                            } else {
                                System.out.printf("You only have %d tasks!%n",
                                        storedTaskCount);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("You did not provide a number!");
                        }
                        break;
                    case "event":
                        int sl = nl.indexOf('/');
                        if (sl == -1) {
                            System.out.println("I need to know the duration of the event.");
                        } else {
                            String d = nl.substring(t + 1, sl);
                            if (validDescriptor(d)) {
                                storedTasks.add(new Event(d, nl.substring(sl + 1)));
                                System.out.printf("How nice, you have something to attend.%n%s%n",
                                        storedTasks.get(storedTaskCount).toString());
                                storedTaskCount++;
                            } else {
                                System.out.println("Please give a valid description.");
                            }
                        }
                        break;
                    case "deadline":
                        int slash = nl.indexOf('/');
                        if (slash == -1) {
                            System.out.println("I need to know the deadline.");
                        } else {
                            String des = nl.substring(t + 1, slash);
                            String da = nl.substring(slash + 1);
                            try {
                                if (validDescriptor(des)) {
                                    storedTasks.add(new Deadline(des, LocalDate.parse(da, dateParser)));
                                    System.out.printf("That looks urgent.%n%s%n",
                                            storedTasks.get(storedTaskCount).toString());
                                    storedTaskCount++;
                                } else {
                                    System.out.println("Please give a valid description.");
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("The date provided cannot be recognised!");
                            }
                        }
                        break;
                    case "todo":
                        String d = nl.substring(t + 1);
                        if (validDescriptor(d)) {
                            storedTasks.add(new ToDo(d));
                            System.out.printf("You better do that.%n%s%n",
                                    storedTasks.get(storedTaskCount).toString());
                            storedTaskCount++;
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
}
