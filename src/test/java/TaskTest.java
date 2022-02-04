import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.Task;

public class TaskTest {
    @Test
    public void markTask_alreadyMarked_fail() {
        assertFalse(new Task("homework", true).markDone());
    }
}
