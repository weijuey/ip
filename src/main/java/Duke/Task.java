package Duke;

/**
 * Class representing a task the user has entered into Duke.
 * Tasks have a general description given by the user, and a
 * status indicating completion.
 */
public class Task {
    /** Describes the task */
    private String descriptor;

    /** Whether task is completed */
    private boolean completed;

    public Task(String descriptor) {
        this.descriptor = descriptor;
        this.completed = false;
    }

    public Task(String descriptor, boolean completed) {
        this.descriptor = descriptor;
        this.completed = completed;
    }

    /**
     * Updates the completion status of the task to completed.
     * This method does not do anything if task is already completed.
     * Returns whether the mark operation is executed.
     *
     * @return true if completion status is changed, false if not changed.
     */
    public boolean markDone() {
        if (!this.completed) {
            this.completed = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the completion status of the task to not yet completed.
     * This method does not do anything if task is already not
     * yet completed.
     * Returns whether the mark operation is executed.
     *
     * @return true if completion status is changed, false if not changed.
     */
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
