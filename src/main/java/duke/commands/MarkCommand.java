package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Duke command for marking a task in the list
 * as complete by giving its index.
 */
public class MarkCommand extends Command {
    /** Index of task in list to mark completed */
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        if (index >= lst.getLength()) {
            return "You only have " + lst.getLength() + " tasks!";
        } else if (index < 0) {
            return "Invalid index";
        }

        if (lst.mark(index)) {
            saved.toggleCompleted(index);
            return "Marked done:\n" + lst.get(index) + "\n";
        } else {
            return "That's already done.";
        }
    }
}
