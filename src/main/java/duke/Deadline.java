package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a task with a deadline.
 */
public class Deadline extends Task {
    /** Deadline of the task */
    protected LocalDateTime deadline;

    /**
     * Creates Deadline object
     * @param descriptor descriptor
     * @param deadline task deadline
     */
    public Deadline(String descriptor, LocalDateTime deadline) {
        super(descriptor);
        this.deadline = deadline;
    }

    /**
     * Creates Deadline Object and can specify completion
     * @param descriptor descriptor
     * @param deadline task deadline
     * @param isCompleted true or false
     */
    public Deadline(String descriptor, LocalDateTime deadline, boolean isCompleted) {
        super(descriptor, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("hh:mma, dd MMM yyyy")));
    }
}
