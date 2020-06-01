package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.Fanfare;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public final class DreadDragon extends Follower {
    public DreadDragon(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.DRAGONCRAFT, 7,
                4, 4,
                6, 6);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public List<List<Choice>> getChoices() {
                return Collections.singletonList(owner.getLeader().getEnemyEffectTargetFollowerChoices());
            }

            @Override
            public void settle() throws GameEndingException {
                if (options == null || options.isEmpty()) return;
                report(null);
                final Follower target = (Follower) owner.getLeader().getOpponent().getArea()[options.get(0).getValue()];
                effectDamage(4, target);
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Dread Dragon";
    }
}
