package model.effect;

import model.card.CardBase;

public abstract class CostModifyingEffect extends EffectBase {
    public CostModifyingEffect(CardBase owner) {
        super(owner);
    }
}
