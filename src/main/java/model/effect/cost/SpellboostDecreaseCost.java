package model.effect.cost;

import model.card.spell.Spellboost;

public class SpellboostDecreaseCost implements CostOperator {
    private Spellboost spellboost;

    public SpellboostDecreaseCost(final Spellboost spellboost) {
        this.spellboost = spellboost;
    }

    @Override
    public int applyAsInt(int cost) {
        return Math.max(0, cost - spellboost.getSpellboost());
    }
}
