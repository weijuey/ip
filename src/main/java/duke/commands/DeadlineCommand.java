package duke.commands;

import java.time.LocalDateTime;

import duke.Deadline;
import duke.Storage;
import duke.TaskList;

/**
 * Duke command for creating a Deadline and adding it
 * to current tasks.
 */
public class DeadlineCommand extends Command {
    /** The description given by user */
    private String description;

    /** The deadline given by user */
    private LocalDateTime dateTime;

    /**
     * Returns a DeadlineCommand object with required parameters
     * to create a Deadline object
     * @param description given description
     * @param dateTime given deadline of task
     */
    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    private String savedTextFormat() {
        return "D0" + description + "|" + dateTime.toString() + "\n";
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        Deadline toAdd = new Deadline(description, dateTime);
        lst.addTask(toAdd);
        saved.addTask(this.savedTextFormat());
        return "That looks urgent.\n" + toAdd.toString() + "\n";
    }
}
