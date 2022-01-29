package duke;

/**
 * Duke command for listing all tasks currently
 * entered into the program.
 */
public class ListCommand extends Command {
    public void execute(TaskList lst, Ui ui, Storage saved) {
        ui.print(lst.toString());
    }
}
