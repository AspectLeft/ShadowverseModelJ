package model.effect;

import model.card.Follower;
import model.event.EventBase;
import model.event.TurnEndEvent;

public class OneTurnIncrementalBuff extends IncrementalBuff {
    private boolean isTurnEnd = false;

    public OneTurnIncrementalBuff(final Follower owner, final int attackBuff, final int defenseBuff) {
        super(owner, attackBuff, defenseBuff);
    }

    @Override
    public boolean canTrigger(EventBase eventBase) {
        if  (eventBase instanceof TurnEndEvent && ((TurnEndEvent) eventBase).getLeader() == owner.getLeader()) {
            isTurnEnd = true;
            return true;
        }
        return false;
    }

    @Override
    public void settle() {
        if (isTurnEnd) {
            ((Follower) owner).detachBuff(this);
        }
        else {
            super.settle();
        }
    }
}
