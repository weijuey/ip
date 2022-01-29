package duke;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String descriptor;

    private LocalDateTime dateTime;

    public DeadlineCommand(String descriptor, LocalDateTime dateTime) {
        this.descriptor = descriptor;
        this.dateTime = dateTime;
    }

    public void execute(TaskList lst, Ui ui, Storage saved) {
        Deadline toAdd = new Deadline(descriptor, dateTime);
        lst.addTask(toAdd);
        saved.addTask("D0" + descriptor + "|" + dateTime.toString());
        ui.print("That looks urgent.\n" + toAdd.toString() + "\n");

    }
}
