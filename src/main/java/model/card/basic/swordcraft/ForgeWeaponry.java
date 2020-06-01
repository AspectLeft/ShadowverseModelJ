package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.SpellWithOptions;
import model.effect.Choice;
import model.effect.EffectWithOptions;
import model.effect.IncrementalBuff;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public class ForgeWeaponry extends SpellWithOptions {
    public ForgeWeaponry(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 3, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Follower target = (Follower) owner.getLeader().getArea()[options.get(0).getValue()];
                final int buffValue = owner.getLeader().getRecord("Rally") >= 10 ? 4 : 2;
                final IncrementalBuff buff = new IncrementalBuff(target, buffValue, buffValue);
                target.attachEffect(buff);
                buff.settle();
            }
        });
    }

    @Override
    public String getName() {
        return "Forge Weaponry";
    }

    @Override
    public List<List<Choice>> getChoices() {
        return Collections.singletonList(getLeader().getAlliedFollowerChoices());
    }
}
