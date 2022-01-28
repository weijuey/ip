public class Task {
    private String descriptor;

    private boolean completed;

    public Task(String descriptor) {
        this.descriptor = descriptor;
        this.completed = false;
    }

    public boolean markDone() {
        if (!this.completed) {
            this.completed = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean markUndone() {
        if (this.completed) {
            this.completed = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        char c = completed ? 'X' : ' ';
        return String.format("[%c] %s", c, descriptor);
    }
}
