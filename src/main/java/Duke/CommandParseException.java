package Duke;

public class CommandParseException extends Exception {
    private String original;

    public CommandParseException(String message, String original) {
        super(message);
        this.original = original;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ":\n" + original;
    }
}
