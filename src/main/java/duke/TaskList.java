package duke;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class wrapping an ArrayList that stores the tasks
 * entered into the program. Allows changing completion
 * status of specific stored tasks by passing its index,
 * which can be seen via the string representation.
 */
public class TaskList {
    private ArrayList<Task> lst;


    public TaskList() {
        lst = new ArrayList<>();
    }

    public int getLength() {
        return lst.size();
    }

    public void addTask(Task t) {
        lst.add(t);
    }

    /**
     * Deletes task at index given.
     * @param index of task
     */
    public void deleteTask(int index) {
        assert index >= 0;
        assert index < lst.size();
        lst.remove(index);
    }

    public void deleteAll() {
        lst.clear();
    }

    /**
     * Marks the task at given index as completed.
     * @param index of task
     * @return true if task is successfully changed to completed, false otherwise
     */
    public boolean mark(int index) {
        assert index >= 0;
        assert index < lst.size();
        return lst.get(index).markDone();
    }

    /**
     * Marks the task at given index as incomplete.
     * @param index of task
     * @return true if task is successfully changed to incomplete, false otherwise
     */
    public boolean unmark(int index) {
        assert index >= 0;
        assert index < lst.size();
        return lst.get(index).markNotDone();
    }

    public Task get(int index) {
        assert index >= 0;
        assert index < lst.size();
        return lst.get(index);
    }

    @Override
    public String toString() {
        if (lst.size() == 0) {
            return "You are free.";
        } else {
            StringBuilder res = new StringBuilder();
            for (int i = 1; i <= lst.size(); i++) {
                res.append(i).append(": ").append(lst.get(i - 1).toString()).append("\n");
            }
            return res.toString();
        }
    }

    public void sort(Comparator<Task> sortBy) {
        this.lst.sort(sortBy);
    }
}
