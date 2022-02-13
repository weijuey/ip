package duke.commands;

import java.util.Comparator;

import duke.Deadline;
import duke.Event;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Todo;

public class SortCommand extends Command {
    private static Comparator<Task> categoricalSort = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            if (o1 instanceof Deadline) {
                if (o2 instanceof Deadline) {
                    return o1.compareTo(o2);
                } else {
                    return -1;
                }
            } else if (o1 instanceof Event) {
                if (o2 instanceof Deadline) {
                    return 1;
                } else if (o2 instanceof Event) {
                    return o1.compareTo(o2);
                } else {
                    return -1;
                }
            } else if (o1 instanceof Todo) {
                if (o2 instanceof Deadline || o2 instanceof Event) {
                    return 1;
                } else if (o2 instanceof Todo) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return 1;
            }
        }
    };

    private static Comparator<Task> chronoSort = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.compareTo(o2);
        }
    };

    public enum Order {
        CATERGORY,
        CHRONO
    }

    private Order order;

    public SortCommand(Order order) {
        this.order = order;
    }

    public static Order getOrder(String cmdWord) {
        if (cmdWord.equalsIgnoreCase("date")) {
            return Order.CHRONO;
        } else if (cmdWord.equalsIgnoreCase("type")) {
            return Order.CATERGORY;
        } else {
            return null;
        }
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        switch (this.order) {
        case CATERGORY:
            lst.sort(categoricalSort);
            break;
        case CHRONO:
            lst.sort(chronoSort);
            break;
        default:
            return "Unrecognised order";
        }
        saved.writeToSaved(lst);
        return "Done!\n" + lst.toString();
    }
}
