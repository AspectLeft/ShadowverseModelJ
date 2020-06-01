package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.Choice;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public final class BlackenedScripture extends EnemyFollowerTargetingSpell {
    public BlackenedScripture(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().getOpponent().getArea()[options.get(0).getValue()].tryBanish();
            }
        });
    }

    @Override
    public List<List<Choice>> getChoices() {
        return Collections.singletonList(leader.getEnemyEffectTargetChoicesWithFilter(
                card -> card instanceof Follower && ((Follower) card).getDefense() <= 3));
    }

    @Override
    public String getName() {
        return "Blackened Scripture";
    }
}
