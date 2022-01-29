package duke;

/**
 * Duke Command for deleting a task from the list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList lst, Ui ui, Storage saved) {
        if (index < lst.getLength()) {
            Task toDelete = lst.get(index);
            lst.deleteTask(index);
            saved.deleteTask(index);
            ui.print("Great, we got this out of the way.\n" + toDelete.toString()
                    + "\n");
        } else {
            ui.print("You only have " + index + " tasks!");
        }
    }
}
