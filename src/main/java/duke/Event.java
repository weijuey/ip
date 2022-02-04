package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing an event, a form of task
 * with a specified time.
 */
public class Event extends Task {
    /** The time of the event given by user */
    protected LocalDateTime time;

    /**
     * Creates Event object
     * @param description description
     * @param time time of the event
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Creates Event object and can specify completion
     * @param description description
     * @param time time of the event
     * @param isCompleted true or false
     */
    public Event(String description, LocalDateTime time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("hh:mma, dd MMM yyyy")));
    }
}
