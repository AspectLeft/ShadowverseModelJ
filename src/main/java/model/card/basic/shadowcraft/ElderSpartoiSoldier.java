package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public final class ElderSpartoiSoldier extends Follower {
    public ElderSpartoiSoldier(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 4,
                4, 3,
                6, 5);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().increaseShadow(2);
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Elder Spartoi Soldier";
    }
}
