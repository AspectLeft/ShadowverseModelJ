package model.effect;

import model.Entity;
import model.event.EventBase;

public abstract class TriggerAbleEffect extends EffectBase {
    public TriggerAbleEffect(Entity entity) {
        super(entity);
    }

    public abstract boolean canTrigger(final EventBase eventBase);
}
