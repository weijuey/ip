package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Duke command for listing all tasks currently
 * entered into the program.
 */
public class ListCommand extends Command {
    public String execute(TaskList lst, Storage saved) {
        return (lst.toString());
    }
}
