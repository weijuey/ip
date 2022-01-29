package duke;

import java.io.IOException;

public class Duke {

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
