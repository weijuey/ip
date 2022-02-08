package duke;

/**
 * Task representing a task with no deadline.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
