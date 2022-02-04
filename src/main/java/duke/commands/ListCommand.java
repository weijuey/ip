package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Duke command for listing all tasks currently
 * entered into the program.
 */
public class ListCommand extends Command {
    public void execute(TaskList lst, Ui ui, Storage saved) {
        ui.print(lst.toString());
    }
}
