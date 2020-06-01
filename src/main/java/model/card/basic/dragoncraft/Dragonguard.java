package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.OverflowEffect;
import model.effect.Ward;
import model.exception.GameEndingException;

public final class Dragonguard extends Follower {
    public Dragonguard(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.DRAGONCRAFT, 6,
                5, 6,
                7, 8);
    }

    @Override
    protected void initEffects() {
        OverflowEffect effect = new OverflowEffect(this) {
            final Ward ward = new Ward((Follower) owner);

            @Override
            public void settle() throws GameEndingException {
                final Follower owner = (Follower) getOwner();
                if (overflowActivated) {
                    report(null);
                    owner.attachEffect(ward);
                } else {
                    if (owner.detachEffect(ward)) {
                        report(null);
                    }
                }
            }
        };
        unevolvedEffects.add(effect);
        evolvedEffects.add(effect);
    }

    @Override
    public String getName() {
        return "Dragonguard";
    }
}
