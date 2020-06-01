package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.forestcraft.token.Fairy;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

public class SylvanJustice extends EnemyFollowerTargetingSpell {
    public SylvanJustice(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = (Leader) getOwner();
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                leader.spellDamage(2, target);
                leader.putCardIntoHand(new Fairy(leader));
            }
        });
    }

    @Override
    public String getName() {
        return "Sylvan Justice";
    }
}
