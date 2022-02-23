package utils;

import java.time.LocalDateTime;

import duke.Deadline;
import duke.Event;
import duke.TaskList;
import duke.Todo;

/**
 * Utility class providing standard tasks and tasklist for testing.
 */
public class TestTaskList {
    public static final Deadline FIRST_TEST_TASK = new Deadline("Next project iteration",
            LocalDateTime.parse("2022-03-01T23:59"));
    public static final Event SECOND_TEST_TASK = new Event("Blockchain Seminar",
            LocalDateTime.parse("2022-02-20T16:00"), true);
    public static final Deadline THIRD_TEST_TASK = new Deadline("Deliver v1.1 product",
            LocalDateTime.parse("2022-02-22T23:59"));
    public static final Todo FOURTH_TEST_TASK = new Todo("Play Minecraft");
    public static final Event FIFTH_TEST_TASK = new Event("v1.2 presentation",
            LocalDateTime.parse("2022-02-28T14:00"));

    public static TaskList getTestTaskList() {
        TaskList defaultTaskList = new TaskList();
        defaultTaskList.addTask(FIRST_TEST_TASK);
        defaultTaskList.addTask(SECOND_TEST_TASK);
        defaultTaskList.addTask(THIRD_TEST_TASK);
        defaultTaskList.addTask(FOURTH_TEST_TASK);
        defaultTaskList.addTask(FIFTH_TEST_TASK);
        return defaultTaskList;
    }


}
