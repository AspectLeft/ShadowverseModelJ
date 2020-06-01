package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Storm;

public final class ImpLancer extends Follower {
    public ImpLancer(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 6,
                3, 6,
                5, 8);
    }

    @Override
    protected void initEffects() {
        final Storm storm = new Storm(this);
        unevolvedEffects.add(storm);
        evolvedEffects.add(storm);
    }

    @Override
    public String getName() {
        return "Imp Lancer";
    }
}
