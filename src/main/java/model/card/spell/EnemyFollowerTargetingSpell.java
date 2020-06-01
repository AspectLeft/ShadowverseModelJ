package model.card.spell;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.EffectBase;

import java.util.Collections;
import java.util.List;

public abstract class EnemyFollowerTargetingSpell extends SpellWithOptions {
    protected EnemyFollowerTargetingSpell(final Leader leader, final Rarity rarity, final CardClass defaultCardClass,
                                          final int defaultCost, final EffectBase effect) {
        super(leader, rarity, defaultCardClass, defaultCost, effect);
    }

    @Override
    public List<List<Choice>> getChoices() {
        return Collections.singletonList(getLeader().getEnemyEffectTargetFollowerChoices());
    }
}
