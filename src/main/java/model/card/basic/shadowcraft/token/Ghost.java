package model.card.basic.shadowcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.LeavingIsBanishing;
import model.effect.Storm;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.TurnEndEvent;
import model.exception.GameEndingException;

public final class Ghost extends Follower {
    public Ghost(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 1,
                1, 1,
                3, 3);
    }

    @Override
    protected void initEffects() {
        final Storm storm = new Storm(this);
        unevolvedEffects.add(storm);
        evolvedEffects.add(storm);

        final LeavingIsBanishing leavingIsBanishing = new LeavingIsBanishing(this);
        unevolvedEffects.add(leavingIsBanishing);
        evolvedEffects.add(leavingIsBanishing);

        final TriggerAbleEffect autoBanish = new TriggerAbleEffect(this) {
            @Override
            public boolean canTrigger(EventBase eventBase) {
                return eventBase instanceof TurnEndEvent && ((TurnEndEvent) eventBase).getLeader() == owner.getLeader();
            }

            @Override
            public void settle() throws GameEndingException {
                if (!((Follower) owner).isInArea()) return;
                report(null);
                ((Follower) owner).tryBanish();
            }
        };

        unevolvedEffects.add(autoBanish);
        evolvedEffects.add(autoBanish);
    }

    @Override
    public String getName() {
        return "Ghost";
    }
}
