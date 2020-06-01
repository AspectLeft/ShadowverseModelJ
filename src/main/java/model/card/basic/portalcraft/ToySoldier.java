package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.portalcraft.token.Puppet;
import model.effect.Fanfare;
import model.effect.IncrementalBuff;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.FollowerComesIntoPlayEvent;
import model.exception.GameEndingException;

import java.util.LinkedList;
import java.util.Queue;

public final class ToySoldier extends Follower {
    public ToySoldier(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 3,
                2, 1,
                4, 3);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.putCardIntoHand(new Puppet(leader));
            }
        };
        unevolvedEffects.add(fanfare);

        final TriggerAbleEffect buffingPuppet = new TriggerAbleEffect(this) {
            final Queue<Puppet> targetQueue = new LinkedList<>();

            @Override
            public boolean canTrigger(final EventBase eventBase) {
                if (! (eventBase instanceof FollowerComesIntoPlayEvent)) return false;
                final CardBase target = ((FollowerComesIntoPlayEvent) eventBase).getCard();
                if (target.getLeader() == owner.getLeader() && target instanceof Puppet) {
                    targetQueue.add((Puppet) target);
                    return true;
                }
                return false;
            }

            @Override
            public void settle() throws GameEndingException {
                if (!((Follower)owner).isInArea()) return;
                final Puppet target = targetQueue.poll();
                if (target == null || !target.isInArea()) return;
                report(null);
                final IncrementalBuff buff = new IncrementalBuff(target, 1, 0);
                target.attachEffect(buff);
                buff.settle();
            }
        };
        evolvedEffects.add(buffingPuppet);
    }

    @Override
    public String getName() {
        return "Toy Soldier";
    }
}
