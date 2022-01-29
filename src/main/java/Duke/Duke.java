package Duke;

import java.io.IOException;

public class Duke {
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE
    }

    private Ui ui;

    private Parser parser;

    private TaskList storedTasks;

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
                parser.parse(nl, storedTasks, savedTasks, ui);
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
