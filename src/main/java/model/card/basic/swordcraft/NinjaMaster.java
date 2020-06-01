package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.Ambush;

public final class NinjaMaster extends Follower {
    public NinjaMaster(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 5,
                4, 4,
                6, 6);
    }

    @Override
    protected void initEffects() {
        final Ambush ambush = new Ambush(this);
        unevolvedEffects.add(ambush);
        evolvedEffects.add(ambush);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    public String getName() {
        return "Ninja Master";
    }
}
