package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class for reading user input and printing to
 * screen to the user.
 */
public class Ui {
    private BufferedReader input;

    public Ui() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() throws IOException {
        return input.readLine();
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    public void printWelcomeMessage() {
        System.out.println("Hi.");
    }

    public void printFareWellMessage() {
        System.out.println("Bye and have a good day.");
    }
}
