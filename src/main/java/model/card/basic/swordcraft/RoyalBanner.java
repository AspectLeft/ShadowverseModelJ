package model.card.basic.swordcraft;

import model.Leader;
import model.card.*;
import model.card.amulet.Amulet;
import model.effect.Fanfare;
import model.effect.IncrementalBuff;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.FollowerComesIntoPlayEvent;
import model.exception.GameEndingException;

import java.util.LinkedList;
import java.util.Queue;

public final class RoyalBanner extends Amulet {
    public RoyalBanner(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.SWORDCRAFT, 4);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.COMMANDER);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().getAlliedFollowers().forEach(follower -> {
                    if (follower.hasTrait(Trait.OFFICER)) {
                        final IncrementalBuff buff = new IncrementalBuff(follower, 1, 0);
                        follower.attachEffect(buff);
                        buff.settle();
                    }
                });
            }
        };

        final TriggerAbleEffect buffingOfficer = new TriggerAbleEffect(this) {
            final Queue<Follower> targetQueue = new LinkedList<>();

            @Override
            public boolean canTrigger(final EventBase eventBase) {
                if (!(eventBase instanceof FollowerComesIntoPlayEvent)) return false;
                Follower target = (Follower) ((FollowerComesIntoPlayEvent) eventBase).getCard();
                if (target.getLeader() == owner.getLeader() && target.hasTrait(Trait.OFFICER)) {
                    targetQueue.add(target);
                    return true;
                }
                return false;
            }

            @Override
            public void settle() throws GameEndingException {
                if (!((AreaTakingCard)owner).isInArea()) return;
                final Follower target = targetQueue.poll();
                if (target == null || !target.isInArea()) return;
                report(null);
                final IncrementalBuff buff = new IncrementalBuff(target, 1, 0);
                target.attachEffect(buff);
                buff.settle();
            }
        };

        effects.add(fanfare);
        effects.add(buffingOfficer);
    }

    @Override
    public String getName() {
        return "Royal Banner";
    }
}
