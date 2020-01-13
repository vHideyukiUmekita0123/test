package exception;

public class NoTestDataException extends AutomatedTestException{

    public NoTestDataException() {
        super();
    }

    public NoTestDataException(String message) {
        super(message);
    }

    public NoTestDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTestDataException(Throwable cause) {
        super(cause);
    }

    protected NoTestDataException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
