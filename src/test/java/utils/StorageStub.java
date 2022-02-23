package utils;

import duke.Storage;
import duke.TaskList;

/**
 * Stub of the Storage class for testing commands.
 */
public class StorageStub extends Storage {
    @Override
    public void writeToSaved(TaskList lst) {
    }

    @Override
    public void toggleCompleted(int index) {
    }

    @Override
    public void addTask(String task) {
    }

    @Override
    public void deleteTask(int index) {
    }

    @Override
    public void deleteAll() {
    }
}
