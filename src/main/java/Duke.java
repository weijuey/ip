import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String welcomeMessage = "Hi";

    private static String farewellMessage = "Bye";

    private static Task[] storedTasks;

    private static int storedTaskCount;

    public static void main(String[] args) throws IOException {
        storedTasks = new Task[100];
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
                            System.out.println(i + ": " + storedTasks[i - 1]);
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
                                storedTasks[task].markDone();
                                System.out.printf("Marked done:%n%s%n"
                                        , storedTasks[task].toString());
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
                                storedTasks[task].markUndone();
                                System.out.printf("Oops! Marked undone:%n%s%n"
                                        , storedTasks[task].toString());
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
                        storedTasks[storedTaskCount] = new Event(nl.substring(t + 1, sl),
                                nl.substring(sl + 1));
                        System.out.printf("How nice, you have something to attend.%n%s%n",
                                storedTasks[storedTaskCount].toString());
                        storedTaskCount++;
                        break;
                    case "deadline":
                        int slash = nl.indexOf('/');
                        storedTasks[storedTaskCount] = new Deadline(nl.substring(t + 1, slash),
                                nl.substring(slash + 1));
                        System.out.printf("That looks urgent.%n%s%n",
                                storedTasks[storedTaskCount].toString());
                        storedTaskCount++;
                        break;
                    case "todo":
                        storedTasks[storedTaskCount] = new ToDo(nl.substring(t + 1));
                        System.out.printf("You better do that.%n%s%n",
                                storedTasks[storedTaskCount].toString());
                        storedTaskCount++;
                        break;
                    default:
                        System.out.println("Sorry, you did not give a valid command.");
                }
            }
        }
    }
}
