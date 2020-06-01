package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.Evolve;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public final class WardrobeRaider extends Follower {
    public WardrobeRaider(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.BLOODCRAFT, 4,
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
                report(null);
                Follower target = null;
                if (options != null && !options.isEmpty()) {
                    target = (Follower) owner.getLeader().getOpponent().getArea()[options.get(0).getValue()];
                }
                if (target != null) {
                    owner.effectDamage(2, target);
                }
                owner.getLeader().heal(2);
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Wardrobe Raider";
    }
}
