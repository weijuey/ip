package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import utils.StorageStub;
import utils.TestTaskList;


class FindCommandTest {
    private final TaskList defaultTaskList = TestTaskList.getTestTaskList();
    private final Storage storageStub = new StorageStub();

    @Test
    public void execute_findPro_successMessage() {
        String expectedResult = "Looking for these?\n1: " + TestTaskList.FIRST_TEST_TASK + "\n2: "
                + TestTaskList.THIRD_TEST_TASK + "\n";
        FindCommand findCommand = new FindCommand("pro");
        String actualResult = findCommand.execute(defaultTaskList, storageStub);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void execute_findDeep_noneFoundMessage() {
        String expectedResult = "No tasks found!";
        FindCommand findCommand = new FindCommand("deep");
        String actualResult = findCommand.execute(defaultTaskList, storageStub);
        assertEquals(expectedResult, actualResult);
    }
}
