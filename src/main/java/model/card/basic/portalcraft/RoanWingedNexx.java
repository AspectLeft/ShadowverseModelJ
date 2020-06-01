package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.Evolve;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public class RoanWingedNexx extends Follower {
    public RoanWingedNexx(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.PORTALCRAFT, 4,
                3, 4,
                5, 6);
    }

    @Override
    protected void initEffects() {
        final Evolve evolve = new Evolve(this) {
            @Override
            public List<List<Choice>> getChoices() {
                if (! owner.getLeader().isResonance()) return null;
                return Collections.singletonList(owner.getLeader().getEnemyEffectTargetFollowerChoices());
            }

            @Override
            public void settle() throws GameEndingException {
                if (! owner.getLeader().isResonance()) return;
                report(null);
                effectDamage(3, owner.getLeader().getOpponent().getArea()[options.get(0).getValue()]);
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Roan Winged Nexx";
    }
}
