package model.card.basic.havencraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class HolywingDragon extends Follower {
    public HolywingDragon(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 6,
                6, 6,
                8, 8);
    }

    @Override
    public String getName() {
        return "Holywing Dragon";
    }
}
