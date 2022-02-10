package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;

public class FindCommand extends Command {
    /** String that user is searching for */
    private String searching;

    public FindCommand(String searching) {
        this.searching = searching;
    }

    @Override
    public String execute(TaskList lst, Storage saved) {
        StringBuilder output = new StringBuilder();
        int foundIndex = 1;
        for (int i = 0; i < lst.getLength(); i++) {
            Task currentTask = lst.get(i);
            if (currentTask.containsString(searching)) {
                output.append(foundIndex).append(": ").append(currentTask.toString()).append("\n");
                foundIndex++;
            }
        }
        if (foundIndex == 1) {
            return "No tasks found!";
        } else {
            assert output.toString().length() > 0;
            output.insert(0, "Looking for these?\n");
            return output.toString();
        }
    }
}
