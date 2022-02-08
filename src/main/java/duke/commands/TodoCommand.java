package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Todo;

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

    private String savedTestFormat() {
        return "T0" + descriptor + "|\n";
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        Todo toAdd = new Todo(descriptor);
        lst.addTask(toAdd);
        saved.addTask(this.savedTestFormat());
        return "You better do that.\n" + toAdd.toString() + "\n";
    }
}
