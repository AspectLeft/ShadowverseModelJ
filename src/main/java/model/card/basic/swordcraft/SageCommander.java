package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public class SageCommander extends Follower {
    public SageCommander(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.SWORDCRAFT, 6,
                4, 6,
                6, 8);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.COMMANDER);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                for (final Follower follower: owner.getLeader().getAlliedFollowers()) {
                    if (follower == owner) continue;
                    follower.attachAndSettleIncrementalBuff(1, 1);
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Sage Commander";
    }
}
