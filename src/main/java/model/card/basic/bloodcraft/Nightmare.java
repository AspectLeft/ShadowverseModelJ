package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public class Nightmare extends Follower {
    public Nightmare(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                if (owner.getLeader().isVengeance()) {
                    report(null);
                    ((Follower) owner).attachAndSettleIncrementalBuff(2, 0);
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Nightmare";
    }
}
