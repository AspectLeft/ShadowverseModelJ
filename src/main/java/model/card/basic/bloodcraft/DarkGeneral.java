package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.effect.Storm;
import model.exception.GameEndingException;

public final class DarkGeneral extends Follower {
    public DarkGeneral(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 4,
                4, 3,
                6, 5);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                if (owner.getLeader().isVengeance()) {
                    report(null);
                    ((Follower) owner).attachEffect(new Storm((Follower) owner));
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Dark General";
    }
}
