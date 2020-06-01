package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.OneTurnIncrementalBuff;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.FollowerStrikeEvent;
import model.exception.GameEndingException;

public final class DisasterDragon extends Follower {
    public DisasterDragon(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.DRAGONCRAFT, 5,
                4, 5,
                6, 7);
    }

    @Override
    protected void initEffects() {
        final TriggerAbleEffect onStrike = new TriggerAbleEffect(this) {
            @Override
            public boolean canTrigger(EventBase eventBase) {
                return eventBase instanceof FollowerStrikeEvent && ((FollowerStrikeEvent) eventBase).getCard() == owner;
            }

            @Override
            public void settle() throws GameEndingException {
                final Follower follower = (Follower) owner;
                if (! follower.isInArea()) return;
                final OneTurnIncrementalBuff buff = new OneTurnIncrementalBuff(follower, 2, 0);
                follower.attachEffect(buff);
                buff.settle();
            }
        };

        unevolvedEffects.add(onStrike);
        evolvedEffects.add(onStrike);
    }

    @Override
    public String getName() {
        return "Disaster Dragon";
    }
}
