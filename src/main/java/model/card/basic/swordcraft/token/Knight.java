package model.card.basic.swordcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;

public final class Knight extends Follower {
    public Knight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 1,
                1, 1,
                3, 3);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    public String getName() {
        return "Knight";
    }
}
