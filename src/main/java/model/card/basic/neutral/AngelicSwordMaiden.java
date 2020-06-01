package model.card.basic.neutral;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Ward;

public final class AngelicSwordMaiden extends Follower {
    public AngelicSwordMaiden(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.NEUTRAL, 5,
                2, 6,
                4, 8);
    }

    @Override
    protected void initEffects() {
        final Ward ward = new Ward(this);
        unevolvedEffects.add(ward);
        evolvedEffects.add(ward);
    }

    @Override
    public String getName() {
        return "Angelic Sword Maiden";
    }
}
