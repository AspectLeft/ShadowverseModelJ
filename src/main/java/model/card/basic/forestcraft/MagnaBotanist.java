package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public class MagnaBotanist extends Follower {
    public MagnaBotanist(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.FORESTCRAFT, 6,
                5, 5,
                7, 7);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                if (owner.getLeader().getCountOfCardsPlayedThisTurn() - 1 < 2) return;
                report(null);
                for (final Follower follower: owner.getLeader().getAlliedFollowers()) {
                    follower.attachAndSettleIncrementalBuff(1, 1);
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Magna Botanist";
    }
}
