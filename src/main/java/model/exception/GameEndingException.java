package model.exception;

import model.Leader;

public class GameEndingException extends RuntimeException {
    private final Leader leader;
    private final boolean winning;

    public GameEndingException(final Leader leader, final boolean winning, final String message) {
        super(message);
        this.leader = leader;
        this.winning = winning;
    }
}
