package duke;

/**
 * Exception caused by inability to parse command line input given
 * by user. The original message is attached to the error message.
 */
public class CommandParseException extends Exception {
    /** Original message given by user */
    private String original;

    /**
     * Creates CommandParseException containing error message and
     * original message that caused the exception
     * @param message error message
     * @param original original message
     */
    public CommandParseException(String message, String original) {
        super(message);
        this.original = original;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ":\n" + original;
    }
}
