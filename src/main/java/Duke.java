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
            String c;
            int t = nl.indexOf(' ');
            if (t == -1) {
                c = nl;
            } else {
                c = nl.substring(0, t);
            }
            if (c.equalsIgnoreCase("bye")) {
                System.out.println(farewellMessage);
                System.exit(0);
            } else if (c.equalsIgnoreCase("list")) {
                for (int i = 1; i <= storedTaskCount; i++) {
                    System.out.println(i + ": " + storedTasks[i - 1]);
                }
            } else {
                storedTasks[storedTaskCount] = new Task(c);
                storedTaskCount++;
                System.out.println("added: " + c);
            }
        }
    }
}
