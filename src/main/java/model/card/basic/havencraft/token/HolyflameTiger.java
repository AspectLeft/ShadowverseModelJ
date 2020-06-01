package model.card.basic.havencraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class HolyflameTiger extends Follower {
    public HolyflameTiger(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 4,
                4, 4,
                6, 6);
    }

    @Override
    public String getName() {
        return "Holyflame Tiger";
    }
}
