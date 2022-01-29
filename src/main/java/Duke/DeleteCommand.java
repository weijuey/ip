package Duke;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList lst, Ui ui, Storage saved) {
        if (index < lst.length()) {
            Task toDelete = lst.get(index);
            lst.deleteTask(index);
            ui.print("Great, we got this out of the way.\n" + toDelete.toString()
                    + "\n");
        } else {
            ui.print("You only have " + index + " tasks!");
        }
    }
}
