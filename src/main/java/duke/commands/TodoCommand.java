package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

/**
 * Duke command for creating a to-do and adding it
 * to current tasks.
 */
public class TodoCommand extends Command {
    /** The description given by user */
    private String descriptor;

    public TodoCommand(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage saved) {
        Todo toAdd = new Todo(descriptor);
        lst.addTask(toAdd);
        saved.addTask("T0" + descriptor + "|");
        ui.print("You better do that.\n" + toAdd.toString() + "\n");
    }
}
