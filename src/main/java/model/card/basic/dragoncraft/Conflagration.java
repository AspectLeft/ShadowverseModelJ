package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.Spell;
import model.effect.EffectBase;
import model.exception.GameEndingException;

import java.util.ArrayList;
import java.util.List;

public class Conflagration extends Spell {
    public Conflagration(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.DRAGONCRAFT, 7, new EffectBase(leader) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final List<Follower> targets = new ArrayList<>();
                targets.addAll(owner.getLeader().getAlliedFollowers());
                targets.addAll(owner.getLeader().getOpponent().getAlliedFollowers());
                owner.getLeader().spellDamage(4, targets);
            }
        });
    }

    @Override
    public String getName() {
        return "Conflagration";
    }
}
