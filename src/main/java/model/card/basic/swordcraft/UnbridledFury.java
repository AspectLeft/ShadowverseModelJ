package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

// Updated on 2020/06
public final class UnbridledFury extends EnemyFollowerTargetingSpell {
    public UnbridledFury(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 1, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = (Leader) getOwner();
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                final int x = leader.getAlliedFollowers().size();
                leader.spellDamage(x, target);
            }
        });
    }

    @Override
    public String getName() {
        return "Unbridled Fury";
    }
}
