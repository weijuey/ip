package Duke;

public class ToDo extends Task {
    public ToDo(String descriptor) {
        super(descriptor);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
