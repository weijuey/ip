import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String descriptor, LocalDate deadline) {
        super(descriptor);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
