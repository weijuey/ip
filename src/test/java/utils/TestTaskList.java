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
            LocalDateTime.parse("01-03-2022T23:59"));
    public static final Event SECOND_TEST_TASK = new Event("Blockchain Seminar",
            LocalDateTime.parse("20-02-2022T16:00"), true);
    public static final Deadline THIRD_TEST_TASK = new Deadline("Deliver v1.1 product",
            LocalDateTime.parse("22-02-2022T23:59"));
    public static final Todo FOURTH_TEST_TASK = new Todo("Play Minecraft");
    public static final Event FIFTH_TEST_TASK = new Event("v1.2 presentation",
            LocalDateTime.parse("28-02-2022T14:00"));

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
