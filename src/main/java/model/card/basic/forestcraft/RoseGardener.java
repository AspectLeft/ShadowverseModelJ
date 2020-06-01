package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.Evolve;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public final class RoseGardener extends Follower {
    public RoseGardener(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.FORESTCRAFT, 4,
                4, 3,
                6, 5);
    }

    @Override
    protected void initEffects() {
        final Evolve evolve = new Evolve(this) {
            @Override
            public void settle() throws GameEndingException {
                if (options == null || options.size() == 0) return;
                final Choice targetChoice = options.get(0);
                Follower target;
                switch (targetChoice.getType()) {
                    case SELF_AREA_LEADER:
                        target = (Follower) owner.getLeader().getArea()[targetChoice.getValue()];
                        break;
                    case ENEMY_AREA_LEADER:
                        target = (Follower) owner.getLeader().getOpponent().getArea()[targetChoice.getValue()];
                        break;
                    default:
                        return;
                }
                target.tryReturnToHand();
            }

            @Override
            public List<List<Choice>> getChoices() {
                return Collections.singletonList(owner.getLeader().getBothEffectTargetFollowerChoices());
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Rose Gardener";
    }
}
