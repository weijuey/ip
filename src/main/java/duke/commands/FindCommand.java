package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    /** String that user is searching for */
    private String searching;

    public FindCommand(String searching) {
        this.searching = searching;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage saved) {
        ui.print("Looking for these?\n");
        int foundIndex = 1;
        for (int i = 0; i < lst.getLength(); i++) {
            Task currentTask = lst.get(i);
            if (currentTask.containsString(searching)) {
                ui.print(foundIndex + ": " + currentTask.toString());
                foundIndex++;
            }
        }
    }
}
