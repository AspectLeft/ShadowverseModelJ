package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.IncrementalBuff;
import model.effect.TriggerAbleEffect;
import model.event.AreaTakingCardComesIntoPlayEvent;
import model.event.EventBase;
import model.exception.GameEndingException;

public final class Okami extends Follower {
    public Okami(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 4,
                3, 4,
                5, 6);
    }

    @Override
    protected void initEffects() {
        final TriggerAbleEffect selfBuffing = new TriggerAbleEffect(this) {
            @Override
            public boolean canTrigger(EventBase eventBase) {
                if (!(eventBase instanceof AreaTakingCardComesIntoPlayEvent)) return false;

                CardBase card = ((AreaTakingCardComesIntoPlayEvent) eventBase).getCard();
                return card != owner && card instanceof Follower && card.getLeader() == owner.getLeader();
            }

            @Override
            public void settle() throws GameEndingException {
                if (!((Follower) owner).isInArea()) return;
                report(null);
                IncrementalBuff buff = new IncrementalBuff((Follower)owner, 1, 0);
                ((Follower) owner).attachEffect(buff);
                buff.settle();
            }
        };
        unevolvedEffects.add(selfBuffing);
        evolvedEffects.add(selfBuffing);
    }

    @Override
    public String getName() {
        return "Okami";
    }
}
