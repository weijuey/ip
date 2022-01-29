package duke;

/**
 * Task representing a task with no deadline.
 */
public class ToDo extends Task {
    public ToDo(String descriptor) {
        super(descriptor);
    }

    public ToDo(String descriptor, boolean isCompleted) {
        super(descriptor, isCompleted);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
