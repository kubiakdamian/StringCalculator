package pl.qbsapps.exception;

public class NegativesNotAllowedException extends AppException {
    public NegativesNotAllowedException(String message) {
        super(message);
    }

    public NegativesNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativesNotAllowedException() {
        super("Negatives not allowed");
    }
}
