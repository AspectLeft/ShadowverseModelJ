package model.card.basic.runecraft;

import model.Entity;
import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.spell.EnemyFollowerAndLeaderTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

public class MagicMissile extends EnemyFollowerAndLeaderTargetingSpell {
    public MagicMissile(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = (Leader) getOwner();
                final int targetIndex = options.get(0).getValue();
                final Entity target = targetIndex >= 0 ? leader.getOpponent().getArea()[targetIndex]
                        : leader.getOpponent();
                leader.spellDamage(1, target);
                leader.drawCard(1);
            }
        });
    }

    @Override
    public String getName() {
        return "Magic Missile";
    }
}
