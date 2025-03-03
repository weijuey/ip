package duke.commands;

import java.time.LocalDateTime;

import duke.Event;
import duke.Storage;
import duke.TaskList;

/**
 * Duke command for creating an Event and adding it
 * to current tasks.
 */
public class EventCommand extends Command {
    /** The description given by user */
    private String description;

    /** The deadline given by user */
    private LocalDateTime dateTime;

    /**
     * Returns an EventCommand object with required parameters
     * to create an Event object
     * @param description given description
     * @param dateTime given time of event
     */
    public EventCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        Event toAdd = new Event(description, dateTime);
        lst.addTask(toAdd);
        saved.addTask(toAdd.savedTextFormat());
        return "How nice, you have something to attend.\n"
                + toAdd.toString() + "\n";
    }

}
