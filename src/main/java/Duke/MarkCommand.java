package duke;

/**
 * Duke command for marking a task in the list
 * as complete by giving its index.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList lst, Ui ui, Storage saved) {
        if (index < lst.getLength()) {
            if (lst.mark(index)) {
                saved.toggleMark(index);
                ui.print("Marked done:\n" + lst.get(index) + "\n");
            } else {
                ui.print("That's not done yet.");
            }
        } else {
            ui.print("You only have " + index + " tasks!");
        }
    }
}
