package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Evolve;
import model.exception.GameEndingException;

public final class DemonflameMage extends Follower {
    public DemonflameMage(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.RUNECRAFT, 4,
                3, 4,
                4, 5);
    }

    @Override
    protected void initEffects() {
        final Evolve evolve = new Evolve(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.effectDamage(1, owner.getLeader().getOpponent().getAlliedFollowers());
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Demonflame Mage";
    }
}
