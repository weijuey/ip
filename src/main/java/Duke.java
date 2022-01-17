import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String welcomeMessage = "Hi";

    private static String farewellMessage = "Bye";

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(welcomeMessage);

        while (true) {
            String c = input.readLine();
            if (c.compareTo("bye") == 0) {
                System.out.println(farewellMessage);
                System.exit(0);
            } else {
                System.out.println(c);
            }
        }
    }
}
