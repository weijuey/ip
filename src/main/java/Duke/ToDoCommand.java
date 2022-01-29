package duke;

/**
 * Duke command for creating a to-do and adding it
 * to current tasks.
 */
public class ToDoCommand extends Command {
    private String descriptor;

    public ToDoCommand(String descriptor) {
        this.descriptor = descriptor;
    }

    public void execute(TaskList lst, Ui ui, Storage saved) {
        ToDo toAdd = new ToDo(descriptor);
        lst.addTask(toAdd);
        saved.addTask("T0" + descriptor + "|");
        ui.print("You better do that.\n" + toAdd.toString() + "\n");
    }
}
