package duke;

/**
 * Duke Command for exiting the program.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList lst, Ui ui, Storage saved) {
        ui.printFareWellMessage();
        System.exit(0);
    }
}
