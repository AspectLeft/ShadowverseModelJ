package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public class Treant extends Follower {
    public Treant(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 5,
                4, 4,
                6, 6);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                if (owner.getLeader().getCountOfCardsPlayedThisTurn() - 1 >= 2) {
                    ((Follower) owner).attachAndSettleIncrementalBuff(2, 2);
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Treant";
    }
}
