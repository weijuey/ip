import Duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {
    @Test
    public void markTask_alreadyMarked_fail() {
        assertFalse(new Task("homework", true).markDone());
    }
}
