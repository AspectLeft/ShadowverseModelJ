package model.card.basic.bloodcraft;

import model.Entity;
import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.spell.EnemyFollowerAndLeaderTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

public class RazoryClaw extends EnemyFollowerAndLeaderTargetingSpell {
    public RazoryClaw(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = owner.getLeader();
                final int targetIndex = options.get(0).getValue();
                final Entity target = targetIndex >= 0 ? leader.getOpponent().getArea()[targetIndex]
                        : leader.getOpponent();

                leader.spellDamage(2, leader);
                leader.spellDamage(3, target);
            }
        });
    }

    @Override
    public String getName() {
        return "Razory Claw";
    }
}
