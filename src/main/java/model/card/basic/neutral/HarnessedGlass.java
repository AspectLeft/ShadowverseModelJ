package model.card.basic.neutral;

import model.Leader;
import model.card.AreaTakingCard;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.neutral.token.FlameAndGlass;
import model.effect.StrikeEffect;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.TurnStartEvent;
import model.exception.GameEndingException;

public class HarnessedGlass extends Follower {
    public HarnessedGlass(final Leader leader) {
        super(leader, Rarity.GOLD, CardClass.NEUTRAL, 3,
                1, 2,
                3, 4);
    }

    @Override
    protected void initEffects() {
        final StrikeEffect onStrike = new StrikeEffect(this) {
            @Override
            public void settle() throws GameEndingException {
                if (!((Follower) owner).isInArea()) {
                    return;
                }
                report(null);
                owner.effectDamage(1, owner.getLeader().getOpponent().getAlliedFollowers());
            }
        };
        final TriggerAbleEffect combine = new TriggerAbleEffect(this) {
            @Override
            public boolean canTrigger(final EventBase event) {
                return event instanceof TurnStartEvent && ((TurnStartEvent) event).getLeader() == owner.getLeader();
            }

            @Override
            public void settle() throws GameEndingException {
                if (!((Follower) owner).isInArea()) {
                    return;
                }
                final Leader leader = owner.getLeader();
                final AreaTakingCard harnessedFlame = leader.findAreaInstance(HarnessedFlame.class);
                if (harnessedFlame == null) {
                    return;
                }
                report(null);
                ((Follower) owner).banish();
                harnessedFlame.banish();
                leader.summon(new FlameAndGlass(leader));
            }
        };
        unevolvedEffects.add(onStrike);
        unevolvedEffects.add(combine);
        evolvedEffects.add(onStrike);
        evolvedEffects.add(combine);
    }

    @Override
    public String getName() {
        return "Harnessed Glass";
    }
}
