package model.effect;

import model.card.Follower;
import model.event.EventBase;
import model.event.FollowerStrikeEvent;

public abstract class StrikeEffect extends TriggerAbleEffect {
    public StrikeEffect(final Follower entity) {
        super(entity);
    }

    @Override
    public boolean canTrigger(final EventBase eventBase) {
        return eventBase instanceof FollowerStrikeEvent
                && ((FollowerStrikeEvent) eventBase).getCard() == owner;
    }
}
