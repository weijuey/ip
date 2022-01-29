import Duke.CommandParseException;
import Duke.Parser;
import Duke.UnmarkCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void unmarkTask_invalidNumber_exceptionThrown() {
        try {
            assertEquals(new UnmarkCommand(0), parser.parse("unmark x"));
            fail();
        } catch (CommandParseException e) {
            assertEquals("You did not provide a number!:\nunmark x", e.getMessage());
        }
    }
}
