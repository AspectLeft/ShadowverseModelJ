package model.exception;

public class DataInconsistentException extends RuntimeException {
    public DataInconsistentException(final String msg) {
        super(msg);
    }
}
