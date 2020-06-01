package model.card.basic.runecraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class Snowman extends Follower {
    public Snowman(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 1,
                1, 1,
                3, 3);
    }

    @Override
    public String getName() {
        return "Snowman";
    }
}
