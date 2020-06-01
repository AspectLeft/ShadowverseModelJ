package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public final class CrazedExecutioner extends Follower {
    public CrazedExecutioner(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 3,
                3, 3,
                5, 5);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.effectDamage(2, owner.getLeader());
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Crazed Executioner";
    }
}
