package duke;

/**
 * Class representing a task the user has entered into Duke.
 * Tasks have a general description given by the user, and a
 * status indicating completion.
 */
public class Task {
    /** Describes the task */
    private String descriptor;

    /** Whether task is completed */
    private boolean isCompleted;

    public Task(String descriptor) {
        this.descriptor = descriptor;
        this.isCompleted = false;
    }

    public Task(String descriptor, boolean isCompleted) {
        this.descriptor = descriptor;
        this.isCompleted = isCompleted;
    }

    /**
     * Updates the completion status of the task to completed.
     * This method does not do anything if task is already completed.
     * Returns whether the mark operation is executed.
     *
     * @return true if completion status is changed, false if not changed.
     */
    public boolean markDone() {
        if (!this.isCompleted) {
            this.isCompleted = true;
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
        if (this.isCompleted) {
            this.isCompleted = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean matchString(String toCompare) {
        return this.descriptor.contains(toCompare);
    }

    @Override
    public String toString() {
        char c = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", c, descriptor);
    }
}
