package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;

/**
 * Duke Command for deleting a task from the list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        if (index < lst.getLength()) {
            if (index < 0) {
                return "Invalid index";
            }
            Task toDelete = lst.get(index);
            lst.deleteTask(index);
            saved.deleteTask(index);
            return "Great, we got this out of the way.\n" + toDelete.toString()
                    + "\n";
        } else {
            return "You only have " + lst.getLength() + " tasks!";
        }
    }
}
