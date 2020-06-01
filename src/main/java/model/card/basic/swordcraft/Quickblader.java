package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.Storm;

// TODO: incomplete
public final class Quickblader extends Follower {
    public Quickblader(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 1,
                1, 1,
                3, 3);
    }

    @Override
    public void initEffects() {
        final Storm storm = new Storm(this);
        unevolvedEffects.add(storm);
        evolvedEffects.add(storm);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    public String getName() {
        return "Quickblader";
    }
}
