package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import utils.StorageStub;
import utils.TestTaskList;

class SortCommandTest {
    private TaskList defaultTaskList;
    private final Storage storageStub = new StorageStub();

    @Test
    void execute_dateOrder_successMessage() {
        defaultTaskList = TestTaskList.getTestTaskList();
        SortCommand sortCommand = new SortCommand(SortCommand.Order.DATE);
        String actualResult = sortCommand.execute(defaultTaskList, storageStub);

        TaskList actualTaskList = new TaskList();
        actualTaskList.addTask(TestTaskList.SECOND_TEST_TASK);
        actualTaskList.addTask(TestTaskList.THIRD_TEST_TASK);
        actualTaskList.addTask(TestTaskList.FIFTH_TEST_TASK);
        actualTaskList.addTask(TestTaskList.FIRST_TEST_TASK);
        actualTaskList.addTask(TestTaskList.FOURTH_TEST_TASK);
        String expectedResult = "Done!\n" + actualTaskList;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void execute_typeOrder_successMessage() {
        defaultTaskList = TestTaskList.getTestTaskList();
        SortCommand sortCommand = new SortCommand(SortCommand.Order.TYPE);
        String actualResult = sortCommand.execute(defaultTaskList, storageStub);

        TaskList actualTaskList = new TaskList();
        actualTaskList.addTask(TestTaskList.THIRD_TEST_TASK);
        actualTaskList.addTask(TestTaskList.FIRST_TEST_TASK);
        actualTaskList.addTask(TestTaskList.SECOND_TEST_TASK);
        actualTaskList.addTask(TestTaskList.FIFTH_TEST_TASK);
        actualTaskList.addTask(TestTaskList.FOURTH_TEST_TASK);
        String expectedResult = "Done!\n" + actualTaskList;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void execute_typeUnrecognised_failureMessage() {
        defaultTaskList = TestTaskList.getTestTaskList();
        SortCommand sortCommand = new SortCommand(SortCommand.Order.UNRECOGNISED);
        String actualResult = sortCommand.execute(defaultTaskList, storageStub);
        String expectedResult = "Unrecognised order! Try:\n" + SortCommand.getValidOrders();
        assertEquals(expectedResult, actualResult);
    }
}
