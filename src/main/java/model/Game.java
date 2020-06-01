package model;

import model.card.CardBase;
import model.card.CardClass;
import util.RandomUtil;

import java.util.Collection;

public class Game {
    private final Leader firstLeader;
    private final Leader secondLeader;

    private boolean isTurnOfFirstLeader;

    public Game(final Leader firstLeader, final Leader secondLeader) {
        this.firstLeader = firstLeader;
        this.secondLeader = secondLeader;
    }

    public Game(final int id1, final CardClass leader1Class, final Collection<CardBase> deck1,
                final int id2, final CardClass leader2Class, final Collection<CardBase> deck2) {
        assert id1 != id2;
        if (RandomUtil.flipCoin()) {
            this.firstLeader = new Leader(id1, leader1Class, true, deck1);
            this.secondLeader = new Leader(id2, leader2Class, false, deck2);
        }
        else {
            this.firstLeader = new Leader(id2, leader2Class, true, deck2);
            this.secondLeader = new Leader(id1, leader1Class, false, deck1);
        }
    }
}
