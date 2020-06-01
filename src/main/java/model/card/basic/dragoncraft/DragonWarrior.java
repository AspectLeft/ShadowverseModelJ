package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.Evolve;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public final class DragonWarrior extends Follower {
    public DragonWarrior(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.DRAGONCRAFT, 4,
                3, 4,
                4, 5);
    }

    @Override
    protected void initEffects() {
        final Evolve evolve = new Evolve(this) {
            @Override
            public List<List<Choice>> getChoices() {
                return Collections.singletonList(owner.getLeader().getEnemyEffectTargetFollowerChoices());
            }

            @Override
            public void settle() throws GameEndingException {
                if (options == null || options.isEmpty()) return;
                final Follower target = (Follower) owner.getLeader().getOpponent().getArea()[options.get(0).getValue()];
                report(null);
                owner.effectDamage(3, target);
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Dragon Warrior";
    }
}
