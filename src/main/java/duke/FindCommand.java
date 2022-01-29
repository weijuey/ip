package duke;

public class FindCommand extends Command {
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
            if (currentTask.matchString(searching)) {
                ui.print(foundIndex + ": " + currentTask.toString());
                foundIndex++;
            }
        }
    }
}
