package model.effect;

import model.card.AreaTakingCard;
import model.event.AreaTakingCardComesIntoPlayEvent;
import model.event.EventBase;
import model.event.MaximumPpChangeEvent;

public abstract class OverflowEffect extends TriggerAbleEffect {
    protected boolean overflowActivated = false;

    public OverflowEffect(AreaTakingCard entity) {
        super(entity);
    }

    @Override
    public boolean canTrigger(final EventBase event) {
        if (event instanceof AreaTakingCardComesIntoPlayEvent
                && ((AreaTakingCardComesIntoPlayEvent) event).getCard() == owner) {
            overflowActivated = owner.getLeader().isOverflow();
            return true;
        }
        if (event instanceof MaximumPpChangeEvent
                && ((MaximumPpChangeEvent) event).getLeader() == owner.getLeader()) {
            overflowActivated = owner.getLeader().isOverflow();
            return true;
        }
        return false;
    }
}
