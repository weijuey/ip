package duke;

/**
 * Abstract class representing the commands supported by Duke.
 * All commands will interact with the current task list, UI and
 * tasks saved to disk in some way, these behaviours are to be
 * implemented by subclasses of this class.
 */
public abstract class Command {
    public abstract void execute(TaskList lst, Ui ui, Storage saved);
}
