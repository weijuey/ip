package duke;

public class ListCommand extends Command {
    public void execute(TaskList lst, Ui ui, Storage saved) {
        ui.print(lst.toString());
    }
}
