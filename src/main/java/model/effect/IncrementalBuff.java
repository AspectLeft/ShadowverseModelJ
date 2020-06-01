package model.effect;

import model.card.Follower;

public class IncrementalBuff extends Buff {
    public IncrementalBuff(final Follower owner, final int attackBuff, final int defenseBuff) {
        super(owner, attackBuff, defenseBuff);
    }
}
