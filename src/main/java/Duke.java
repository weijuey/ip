import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String welcomeMessage = "Hi";

    private static String farewellMessage = "Bye";

    private static String[] storedText;

    private static int storedTextCount;

    public static void main(String[] args) throws IOException {
        storedText = new String[100];
        storedTextCount = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(welcomeMessage);

        while (true) {
            String c = input.readLine();
            if (c.equalsIgnoreCase("bye")) {
                System.out.println(farewellMessage);
                System.exit(0);
            } else {
                storedText[storedTextCount] = c;
                storedTextCount++;
                System.out.println("added: " + c);
            }
        }
    }
}
