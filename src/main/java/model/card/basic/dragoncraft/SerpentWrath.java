package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

public class SerpentWrath extends EnemyFollowerTargetingSpell {
    public SerpentWrath(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.DRAGONCRAFT, 4, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = (Leader) getOwner();
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                leader.spellDamage(6, target);
            }
        });
    }

    @Override
    public String getName() {
        return "Serpent Wrath";
    }
}
