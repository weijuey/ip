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

    public Event(String descriptor, LocalDateTime time) {
        super(descriptor);
        this.time = time;
    }

    public Event(String descriptor, LocalDateTime duration, boolean isCompleted) {
        super(descriptor, isCompleted);
        this.time = duration;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("hh:mma, dd MMM yyyy")));
    }
}
