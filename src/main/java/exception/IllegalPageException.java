package exception;

public class IllegalPageException extends AutomatedTestException {

    public IllegalPageException() {
        super();
    }

    public IllegalPageException(String message) {
        super(message);
    }

    public IllegalPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPageException(Throwable cause) {
        super(cause);
    }

    protected IllegalPageException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
