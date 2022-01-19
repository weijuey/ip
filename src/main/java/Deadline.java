public class Deadline extends Task {
    protected String deadline;

    public Deadline(String descriptor, String deadline) {
        super(descriptor);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by %s)", super.toString(), deadline);
    }
}
