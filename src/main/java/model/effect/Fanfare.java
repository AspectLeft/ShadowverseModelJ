package model.effect;

import model.card.AreaTakingCard;

public abstract class Fanfare extends EffectWithOptions {
    public Fanfare(AreaTakingCard owner) {
        super(owner);
    }
}
