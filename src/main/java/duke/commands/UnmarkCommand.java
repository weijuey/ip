package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Duke command for marking a task in the list
 * as not complete by giving its index.
 */
public class UnmarkCommand extends Command {
    /** Index of task in the tasklist to be marked incomplete */
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        if (index >= lst.getLength()) {
            return "You only have " + lst.getLength() + " tasks!";
        } else if (index < 0) {
            return "Invalid index";
        }

        if (lst.unmark(index)) {
            saved.toggleCompleted(index);
            return "Oops! Marked undone:\n" + lst.get(index) + "\n";
        } else {
            return "You've not done that yet!";
        }
    }
}
