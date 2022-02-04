package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class representing the commands supported by Duke.
 * All commands will interact with the current task list, UI and
 * tasks saved to disk in some way, these behaviours are to be
 * implemented under the abstract execute method by subclasses.
 */
public abstract class Command {
    public abstract void execute(TaskList lst, Ui ui, Storage saved);
}
