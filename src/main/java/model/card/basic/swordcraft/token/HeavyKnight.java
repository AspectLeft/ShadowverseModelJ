package model.card.basic.swordcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;

public final class HeavyKnight extends Follower {
    public HeavyKnight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 1,
                1, 2,
                3, 4);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    public String getName() {
        return "Heavy Knight";
    }
}
