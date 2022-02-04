package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList lst, Ui ui, Storage saved) {
        if (index < lst.getLength()) {
            if (lst.unmark(index)) {
                saved.toggleCompleted(index);
                ui.print("Oops! Marked undone:\n" + lst.get(index) + "\n");
            } else {
                ui.print("You've not done that yet.");
            }
        } else {
            ui.print("You only have " + index + " tasks!");
        }
    }
}
