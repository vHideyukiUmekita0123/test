package exception;

public class ScreenShotException extends RuntimeException {

    public ScreenShotException() {
        super();
    }

    public ScreenShotException(String message) {
        super(message);
    }

    public ScreenShotException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScreenShotException(Throwable cause) {
        super(cause);
    }

    protected ScreenShotException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
