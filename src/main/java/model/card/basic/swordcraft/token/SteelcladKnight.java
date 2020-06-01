package model.card.basic.swordcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;

public final class SteelcladKnight extends Follower {
    public SteelcladKnight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    public String getName() {
        return "Steelclad Knight";
    }
}