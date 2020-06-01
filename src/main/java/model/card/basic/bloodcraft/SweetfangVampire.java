package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Drain;

public final class SweetfangVampire extends Follower {
    public SweetfangVampire(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 2,
                1, 3,
                3, 5);
    }

    @Override
    protected void initEffects() {
        final Drain drain = new Drain(this);
        unevolvedEffects.add(drain);
        evolvedEffects.add(drain);
    }

    @Override
    public String getName() {
        return "Sweetfang Vampire";
    }
}
