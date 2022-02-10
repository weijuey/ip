package duke;

import java.io.IOException;

import duke.commands.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main program of the Duke chatbot. Consists of the
 * user interface, current task list, task list saved
 * on disk, and a parser for user input.
 */
public class Duke extends Application {
    /** Parser to make sense of user input */
    private Parser parser;

    /** Current task list in the program */
    private TaskList storedTasks;

    /** Task list saved on disk */
    private Storage savedTasks;

    /** User interface that reads user input and prints to screen */
    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        this.parser = new Parser();
        this.storedTasks = new TaskList();
        this.savedTasks = new Storage();
        boolean canLoad = false;
        try {
            canLoad = savedTasks.loadSaved(storedTasks);
        } catch (IOException e) {
            storedTasks.deleteAll();
            savedTasks.deleteAll();
        }
        try {
            this.mainWindow = MainWindow.createMainWindow(this, canLoad);
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {
        try {
            assert input != null;
            Command c = parser.parse(input);
            return c.execute(storedTasks, savedTasks);
        } catch (CommandParseException e) {
            return e.getMessage();
        }
    }
}
