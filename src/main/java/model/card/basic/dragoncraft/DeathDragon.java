package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class DeathDragon extends Follower {
    public DeathDragon(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.DRAGONCRAFT, 4,
                4, 4,
                6, 6);
    }

    @Override
    public String getName() {
        return "Death Dragon";
    }
}
