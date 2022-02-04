package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList lst, Ui ui, Storage saved) {
        if (index < lst.getLength()) {
            if (lst.mark(index)) {
                saved.toggleCompleted(index);
                ui.print("Marked done:\n" + lst.get(index) + "\n");
            } else {
                ui.print("That's not done yet.");
            }
        } else {
            ui.print("You only have " + index + " tasks!");
        }
    }
}
