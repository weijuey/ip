package duke;

/**
 * Duke command for marking a task in the list
 * as not complete by giving its index.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList lst, Ui ui, Storage saved) {
        if (index < lst.getLength()) {
            if (lst.unmark(index)) {
                saved.toggleMark(index);
                ui.print("Oops! Marked undone:\n" + lst.get(index) + "\n");
            } else {
                ui.print("You've not done that yet.");
            }
        } else {
            ui.print("You only have " + index + " tasks!");
        }
    }
}
