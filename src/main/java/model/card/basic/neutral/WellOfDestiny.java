package model.card.basic.neutral;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.amulet.Amulet;
import model.effect.IncrementalBuff;
import model.effect.TriggerAbleEffect;
import model.event.EventBase;
import model.event.TurnStartEvent;
import model.exception.GameEndingException;
import util.RandomUtil;

public class WellOfDestiny extends Amulet {

    public WellOfDestiny(Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.NEUTRAL, 2);
    }

    @Override
    protected void initEffects() {
        TriggerAbleEffect effect = new TriggerAbleEffect(this) {
            @Override
            public boolean canTrigger(final EventBase eventBase) {
                return (eventBase instanceof TurnStartEvent) && owner.getLeader() == ((TurnStartEvent) eventBase).getLeader();
            }

            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Follower target = RandomUtil.pickOne(owner.getLeader().getRandom(),
                        owner.getLeader().getAlliedFollowers());
                final IncrementalBuff buff = new IncrementalBuff(target, 1, 1);
                target.attachEffect(buff);
                buff.settle();
            }
        };
        effects.add(effect);
    }

    @Override
    public String getName() {
        return "Well Of Destiny";
    }
}
