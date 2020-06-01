package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.LastWords;
import model.effect.Ward;
import model.exception.GameEndingException;
import util.RandomUtil;

public final class GhostlyRider extends Follower {
    public GhostlyRider(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 6,
                5, 5,
                7, 7);
    }

    @Override
    protected void initEffects() {
        final Ward ward = new Ward(this);
        unevolvedEffects.add(ward);
        evolvedEffects.add(ward);

        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Follower target = RandomUtil.pickOne(owner.getLeader().getRandom(),
                        owner.getLeader().getAlliedFollowers());
                if (target == null) return;
                target.attachEffect(new Ward(target));
            }
        };
        unevolvedEffects.add(lastWords);
        evolvedEffects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Ghostly Rider";
    }
}
