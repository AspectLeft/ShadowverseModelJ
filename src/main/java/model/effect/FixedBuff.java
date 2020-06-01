package model.effect;

import model.card.Follower;

public final class FixedBuff extends Buff {
    public FixedBuff(Follower owner, int attackBuff, int defenseBuff) {
        super(owner, attackBuff, defenseBuff);
    }
}
