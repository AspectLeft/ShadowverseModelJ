package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

public class DimensionCut extends EnemyFollowerTargetingSpell {
    public DimensionCut(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = (Leader) getOwner();
                final int damageValue = leader.isResonance() ? 5 : 3;
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                leader.spellDamage(damageValue, target);
            }
        });
    }

    @Override
    public String getName() {
        return "Dimension Cut";
    }
}
