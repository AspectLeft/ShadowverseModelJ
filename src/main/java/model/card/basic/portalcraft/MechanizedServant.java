package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.effect.Rush;
import model.exception.GameEndingException;

public final class MechanizedServant extends Follower {
    public MechanizedServant(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                if (owner.getLeader().isResonance()) {
                    report(null);
                    final Follower follower = (Follower) owner;
                    follower.attachEffect(new Rush(follower));
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Mechanized Servant";
    }
}
