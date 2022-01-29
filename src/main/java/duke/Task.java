package duke;

public class Task {
    private String descriptor;

    private boolean isCompleted;

    public Task(String descriptor) {
        this.descriptor = descriptor;
        this.isCompleted = false;
    }

    public Task(String descriptor, boolean isCompleted) {
        this.descriptor = descriptor;
        this.isCompleted = isCompleted;
    }

    public boolean markDone() {
        if (!this.isCompleted) {
            this.isCompleted = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean markUndone() {
        if (this.isCompleted) {
            this.isCompleted = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        char c = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", c, descriptor);
    }
}
