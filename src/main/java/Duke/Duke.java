package Duke;

import java.io.IOException;

/**
 * Main program of the Duke chatbot. Consists of the
 * user interface, current task list, task list saved
 * on disk, and a parser of user input.
 */
public class Duke {
    /** User interface that reads user input and prints to screen */
    private Ui ui;

    /** Parser to make sense of user input */
    private Parser parser;

    /** Current task list in the program */
    private TaskList storedTasks;

    /** Task list saved on disk */
    private Storage savedTasks;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storedTasks = new TaskList();
        savedTasks = new Storage();
        try {
            savedTasks.loadSaved(storedTasks);
        } catch (IOException e) {
            ui.print("Can't load saved tasks, starting with empty tasklist");
            storedTasks.deleteAll();
            savedTasks.deleteAll();
        }
        ui.printWelcomeMessage();
    }

    public void run() {
        while (true) {
            try {
                String nl = ui.readLine();
                Command c = parser.parse(nl);
                c.execute(storedTasks, ui, savedTasks);
            } catch (CommandParseException e) {
                ui.print(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
