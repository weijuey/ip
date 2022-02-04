package duke;

/**
 * Class representing a task the user has entered into Duke.
 * Tasks have a general description given by the user, and a
 * status indicating completion.
 */
public class Task {
    /** Describes the task */
    private String description;

    /** Whether task is completed */
    private boolean isCompleted;

    /**
     * Returns task with given description
     * @param description description
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Returns task with given description and completion status
     * @param description description
     * @param isCompleted true or false
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Updates the completion status of the task to complete.
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
     * Updates the completion status of the task to not complete.
     * This method does not do anything if task is already not
     * yet completed.
     * Returns whether the mark operation is executed.
     *
     * @return true if completion status is changed, false if not changed.
     */
    public boolean markNotDone() {
        if (this.isCompleted) {
            this.isCompleted = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the description of the task contains the
     * string provided
     * @param toCompare string to find
     * @return true if description contains string, false otherwise
     */
    public boolean containsString(String toCompare) {
        return this.description.contains(toCompare);
    }

    @Override
    public String toString() {
        char c = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", c, description);
    }
}
