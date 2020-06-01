package model.card.basic.neutral;

import model.Leader;
import model.card.AreaTakingCard;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.neutral.token.FlameAndGlass;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.FollowerStrikeEvent;
import model.event.TurnStartEvent;
import model.exception.GameEndingException;

public class HarnessedFlame extends Follower {
    public HarnessedFlame(final Leader leader) {
        super(leader, Rarity.GOLD, CardClass.NEUTRAL, 3,
                2, 1,
                4, 3);
    }

    @Override
    protected void initEffects() {
        final TriggerAbleEffect onStrike = new TriggerAbleEffect(this) {
            @Override
            public boolean canTrigger(final EventBase eventBase) {
                return eventBase instanceof FollowerStrikeEvent
                        && ((FollowerStrikeEvent) eventBase).getCard() == owner;
            }

            @Override
            public void settle() throws GameEndingException {
                if (!((Follower) owner).isInArea()) {
                    return;
                }
                report(null);
                owner.effectDamage(2, owner.getLeader().getOpponent());
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
                final AreaTakingCard harnessedGlass = leader.findAreaInstance(HarnessedGlass.class);
                if (harnessedGlass == null) {
                    return;
                }
                report(null);
                ((Follower) owner).banish();
                harnessedGlass.banish();
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
        return "Harnessed Flame";
    }
}
