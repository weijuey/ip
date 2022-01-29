package duke;

import java.time.LocalDateTime;

/**
 * Duke command for creating an Event and adding it
 * to current tasks.
 */
public class EventCommand extends Command {
    private String descriptor;

    private LocalDateTime dateTime;

    public EventCommand(String descriptor, LocalDateTime dateTime) {
        this.descriptor = descriptor;
        this.dateTime = dateTime;
    }

    public void execute(TaskList lst, Ui ui, Storage saved) {
        Event toAdd = new Event(descriptor, dateTime);
        lst.addTask(toAdd);
        saved.addTask("E0" + descriptor + "|" + dateTime.toString());
        ui.print("How nice, you have something to attend.\n" +
                toAdd.toString() + "\n");
    }

}
