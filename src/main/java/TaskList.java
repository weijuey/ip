import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public int length() {
        return lst.size();
    }

    public void addTask(Task t) {
        lst.add(t);
    }

    public void deleteTask(int index) {
        lst.remove(index);
    }

    public void deleteAll() {
        lst.clear();
    }

    public boolean mark(int index) {
        return lst.get(index).markDone();
    }

    public boolean unmark(int index) {
        return lst.get(index).markUndone();
    }

    public Task get(int index) {
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
}
