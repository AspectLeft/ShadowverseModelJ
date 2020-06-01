package model.card.amulet;

import model.Leader;
import model.card.AreaTakingCard;
import model.card.CardClass;
import model.card.Rarity;
import model.effect.EffectBase;
import model.event.AmuletComesIntoPlayEvent;
import model.event.AmuletDestroyedEvent;
import model.exception.GameEndingException;
import model.exception.IllegalOperationException;

import java.util.ArrayList;
import java.util.List;

public abstract class Amulet extends AreaTakingCard {
    protected final List<EffectBase> effects = new ArrayList<>();

    private Amulet() {
    }

    public Amulet(final Leader leader, final Rarity rarity, final CardClass defaultCardClass, final int defaultCost) {
        super(leader, rarity, defaultCardClass, defaultCost);
        initEffects();
    }

    @Override
    public void reset() {
        this.effects.clear();
        super.reset();
    }

    @Override
    public List<EffectBase> collectEffects() {
        return effects;
    }

    @Override
    public void comesIntoPlay() throws GameEndingException {
        assert leader != null;
        leader.addCardToArea(this);
        leader.getGame().triggerEffect(new AmuletComesIntoPlayEvent(this));
    }

    @Override
    protected void destroyed() throws GameEndingException {
        super.destroyed();
        this.getGame().triggerEffect(new AmuletDestroyedEvent(this));
    }

    @Override
    public void takeDamage(int damageValue) throws IllegalOperationException {
        throw new IllegalOperationException("Amulet cannot take damage.");
    }
}
