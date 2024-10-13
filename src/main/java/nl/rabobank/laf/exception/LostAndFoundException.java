package nl.rabobank.laf.exception;

/**
 * Custom exception class for handling lost and found related exceptions.
 */
public class LostAndFoundException extends RuntimeException {

    /**
     * Constructs a new LostAndFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public LostAndFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new LostAndFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public LostAndFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new LostAndFoundException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public LostAndFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new LostAndFoundException with the specified detail message, cause,
     * suppression enabled or disabled, and writable stack trace enabled or disabled.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public LostAndFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}