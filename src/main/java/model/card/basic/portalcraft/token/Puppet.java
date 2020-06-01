package model.card.basic.portalcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Rush;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.TurnEndEvent;
import model.exception.GameEndingException;

public final class Puppet extends Follower {
    public Puppet(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 0,
                1, 1,
                3, 3);
    }

    @Override
    protected void initEffects() {
        final Rush rush = new Rush(this);
        final TriggerAbleEffect selfDestroyEffect = new TriggerAbleEffect(this) {
            @Override
            public boolean canTrigger(final EventBase event) {
                if (event instanceof TurnEndEvent) {
                    return ((TurnEndEvent) event).getLeader() == owner.getLeader().getOpponent();
                }
                return false;
            }

            @Override
            public void settle() throws GameEndingException {
                report(null);
                ((Follower) owner).tryDestroy();
            }
        };
        unevolvedEffects.add(rush);
        unevolvedEffects.add(selfDestroyEffect);
        evolvedEffects.add(rush);
        evolvedEffects.add(selfDestroyEffect);
    }

    @Override
    public String getName() {
        return "Puppet";
    }
}
