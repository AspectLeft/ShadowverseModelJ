package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.IncrementalBuff;
import model.effect.OverflowEffect;
import model.exception.GameEndingException;

public final class Dragonrider extends Follower {
    public Dragonrider(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.DRAGONCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initEffects() {
        OverflowEffect effect = new OverflowEffect(this) {
            final IncrementalBuff buff = new IncrementalBuff((Follower) owner, 2, 0);

            @Override
            public void settle() throws GameEndingException {
                final Follower owner = (Follower) getOwner();
                if (overflowActivated) {
                    report(null);
                    owner.attachEffect(buff);
                    buff.settle();
                }
                else {
                    if (owner.detachEffect(buff)) {
                        report(null);
                        owner.refreshBuff();
                    }
                }
            }
        };
        unevolvedEffects.add(effect);
        evolvedEffects.add(effect);
    }

    @Override
    public String getName() {
        return "Dragonrider";
    }
}
