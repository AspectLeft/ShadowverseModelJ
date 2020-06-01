package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

public final class CrimsonPurge extends EnemyFollowerTargetingSpell {
    public CrimsonPurge(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 4, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Follower target = (Follower) owner.getLeader().getOpponent().getArea()[options.get(0).getValue()];
                ((Leader)owner).spellDamage(2, owner);
                target.tryDestroy();
            }
        });
    }

    @Override
    public String getName() {
        return "Crimson Purge";
    }
}
