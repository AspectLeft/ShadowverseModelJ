package model.card.basic.forestcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class Fairy extends Follower {

    public Fairy(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 1,
                1, 1,
                3, 3);
    }

    @Override
    public String getName() {
        return "Fairy";
    }
}
