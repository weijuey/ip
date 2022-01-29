package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime duration;

    public Event(String descriptor, LocalDateTime duration) {
        super(descriptor);
        this.duration = duration;
    }

    public Event(String descriptor, LocalDateTime duration, boolean completed) {
        super(descriptor, completed);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(),
                duration.format(DateTimeFormatter.ofPattern("hh:mma, dd MMM yyyy")));
    }
}
