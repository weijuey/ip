package duke;

public abstract class Command {
    public abstract void execute(TaskList lst, Ui ui, Storage saved);
}
