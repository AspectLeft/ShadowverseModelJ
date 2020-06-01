package model.effect;

import model.card.Follower;
import model.exception.GameEndingException;

public final class FreeToEvolve extends EffectBase {
    FreeToEvolve(Follower owner) {
        super(owner);
    }

    @Override
    public void settle() throws GameEndingException {

    }
}
