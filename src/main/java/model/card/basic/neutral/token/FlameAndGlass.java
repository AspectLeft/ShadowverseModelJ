package model.card.basic.neutral.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Storm;
import model.effect.StrikeEffect;
import model.exception.GameEndingException;

public class FlameAndGlass extends Follower {
    public FlameAndGlass(final Leader leader) {
        super(leader, Rarity.LEGENDARY, CardClass.NEUTRAL, 7,
                7, 7,
                9, 9);
    }

    @Override
    protected void initEffects() {
        final Storm storm = new Storm(this);
        final StrikeEffect onStrike = new StrikeEffect(this) {
            @Override
            public void settle() throws GameEndingException {
                if (!((Follower) owner).isInArea()) {
                    return;
                }
                report(null);
                owner.effectDamage(7, owner.getLeader().getOpponent().getAlliedFollowersAndLeader());
            }
        };
        unevolvedEffects.add(storm);
        unevolvedEffects.add(onStrike);
        evolvedEffects.add(storm);
        evolvedEffects.add(onStrike);
    }

    @Override
    public String getName() {
        return "Flame and Glass";
    }
}
