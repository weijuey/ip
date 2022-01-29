package Duke;

public class ToDo extends Task {
    public ToDo(String descriptor) {
        super(descriptor);
    }

    public ToDo(String descriptor, boolean completed) {
        super(descriptor, completed);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
