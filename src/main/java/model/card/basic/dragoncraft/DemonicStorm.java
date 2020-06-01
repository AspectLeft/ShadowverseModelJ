package model.card.basic.dragoncraft;

import model.Entity;
import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.spell.Spell;
import model.effect.EffectBase;
import model.exception.GameEndingException;

import java.util.ArrayList;
import java.util.List;

public final class DemonicStorm extends Spell {
    public DemonicStorm(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.BLOODCRAFT, 6, new EffectBase(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = owner.getLeader();
                List<Entity> targetList = new ArrayList<>();
                targetList.add(leader);
                targetList.addAll(leader.getAlliedFollowers());
                targetList.add(leader.getOpponent());
                targetList.addAll(leader.getOpponent().getAlliedFollowers());
                owner.getLeader().spellDamage(3, targetList);
            }
        });
    }

    @Override
    public String getName() {
        return "Demonic Storm";
    }
}
