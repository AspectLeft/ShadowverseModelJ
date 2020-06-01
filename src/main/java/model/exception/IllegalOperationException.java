package model.exception;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(final String msg) {
        super(msg);
    }
}
