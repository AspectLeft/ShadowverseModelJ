package model.effect;

import model.card.Follower;
import model.event.EventBase;

public abstract class Buff extends TriggerAbleEffect {
    private int attackBuff;
    private int defenseBuff;

    public Buff(Follower owner, final int attackBuff, final int defenseBuff) {
        super(owner);
        this.attackBuff = attackBuff;
        this.defenseBuff = defenseBuff;
    }

    @Override
    public boolean canTrigger(EventBase eventBase) {
        return false;
    }

    @Override
    public void settle() {
        ((Follower) owner).getBuffed(this);
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    public int getDefenseBuff() {
        return defenseBuff;
    }
}
