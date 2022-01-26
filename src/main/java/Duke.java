import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Duke {
    private static String welcomeMessage = "Hi";

    private static String farewellMessage = "Bye";

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

        Path path = Paths.get("data");
        if (!Files.exists(path)) {
            File d = path.toFile();
            if (!d.mkdir()) {
                System.out.println("Something went wrong, try again");
                System.exit(0);
            }
        }
        Path saved = Paths.get("data", "saved.txt");
        if (!Files.exists(saved)) {
            File s = saved.toFile();
            if (!s.createNewFile()) {
                System.out.println("Something went wrong, try again");
                System.exit(0);
            }
        }
        BufferedReader savedTasks = Files.newBufferedReader(saved);
        String sl = savedTasks.readLine();
        while (sl != null) {
            char type = sl.charAt(0);
            int done = sl.charAt(1);
            int split = sl.indexOf('|',2);
            String des = sl.substring(2, split);
            String da = sl.substring(split + 1);
            switch (type) {
                case 'T':
                    storedTasks.add(new ToDo(des));
                    break;
                case 'D':
                    storedTasks.add(new Deadline(des, da));
                    break;
                case 'E':
                    storedTasks.add(new Event(des, da));
                    break;
            }
            if (done == '1') {
                storedTasks.get(storedTaskCount).markDone();
            }
            storedTaskCount++;
            sl = savedTasks.readLine();
        }
        savedTasks.close();
        
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
                                BufferedReader txt = Files.newBufferedReader(saved);
                                StringBuilder newTxt = new StringBuilder();
                                for (int i = 0; i < storedTaskCount; i++) {
                                    char[] l = txt.readLine().toCharArray();
                                    if (i == task) {
                                        l[1] = '1';
                                    }
                                    newTxt.append(l);
                                }
                                txt.close();
                                BufferedWriter write = Files.newBufferedWriter(saved);
                                write.write(newTxt.toString());
                                write.close();
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
                                BufferedReader txt = Files.newBufferedReader(saved);
                                StringBuilder newTxt = new StringBuilder();
                                for (int i = 0; i < storedTaskCount; i++) {
                                    char[] l = txt.readLine().toCharArray();
                                    if (i == task) {
                                        l[1] = '0';
                                    }
                                    newTxt.append(l);
                                }
                                txt.close();
                                BufferedWriter write = Files.newBufferedWriter(saved);
                                write.write(newTxt.toString());
                                write.close();
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
                                BufferedReader txt = Files.newBufferedReader(saved);
                                StringBuilder newTxt = new StringBuilder();
                                for (int i = 0; i < storedTaskCount; i++) {
                                    String l = txt.readLine();
                                    if (i != task) {
                                        newTxt.append(l);
                                    }
                                }
                                txt.close();
                                BufferedWriter write = Files.newBufferedWriter(saved);
                                write.write(newTxt.toString());
                                write.close();
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
                        int s = nl.indexOf('/');
                        if (s == -1) {
                            System.out.println("I need to know the duration of the event.");
                        } else {
                            String d = nl.substring(t + 1, s);
                            if (validDescriptor(d)) {
                                storedTasks.add(new Event(d, nl.substring(s + 1)));
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
                            String d = nl.substring(t + 1, slash);
                            if (validDescriptor(d)) {
                                storedTasks.add(new Deadline(d, nl.substring(slash + 1)));
                                System.out.printf("That looks urgent.%n%s%n",
                                        storedTasks.get(storedTaskCount).toString());
                                storedTaskCount++;
                            } else {
                                System.out.println("Please give a valid description.");
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
