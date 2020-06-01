package model.card.basic.neutral;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public class Goliath extends Follower {
    public Goliath(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.NEUTRAL, 4,
                3, 4,
                5, 6);
    }

    @Override
    public String getName() {
        return "Goliath";
    }
}
