package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String descriptor, LocalDateTime deadline) {
        super(descriptor);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("hh:mma, dd MMM yyyy")));
    }
}
