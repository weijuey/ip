package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a task with a deadline.
 */
public class Deadline extends Task {
    /** Deadline of the task */
    protected LocalDateTime deadline;

    public Deadline(String descriptor, LocalDateTime deadline) {
        super(descriptor);
        this.deadline = deadline;
    }
    public Deadline(String descriptor, LocalDateTime deadline, boolean completed) {
        super(descriptor, completed);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("hh:mma, dd MMM yyyy")));
    }
}
