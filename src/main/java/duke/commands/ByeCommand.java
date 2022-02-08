package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Duke Command for exiting the program.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList lst, Storage saved) {
        System.exit(0);
        return "";
    }
}
