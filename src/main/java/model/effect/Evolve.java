package model.effect;

import model.card.Follower;

public abstract class Evolve extends EffectWithOptions {
    public Evolve(Follower owner) {
        super(owner);
    }
}
