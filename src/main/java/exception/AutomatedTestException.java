package exception;

public class AutomatedTestException extends Exception {

    public AutomatedTestException() {
        super();
    }

    public AutomatedTestException(String message) {
        super(message);
    }

    public AutomatedTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutomatedTestException(Throwable cause) {
        super(cause);
    }

    protected AutomatedTestException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
