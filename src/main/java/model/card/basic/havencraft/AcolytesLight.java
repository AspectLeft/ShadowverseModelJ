package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

public final class AcolytesLight extends EnemyFollowerTargetingSpell {
    public AcolytesLight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 5, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = (Leader) getOwner();
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                final int healAmount = target.getDefense();
                target.tryBanish();
                leader.heal(healAmount);
            }
        });
    }

    @Override
    public String getName() {
        return "Acolyte's Light";
    }
}
