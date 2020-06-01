package model.card;

import model.Leader;
import model.effect.*;
import model.exception.GameEndingException;
import model.report.Report;

import java.util.List;
import java.util.function.Predicate;

public abstract class AreaTakingCard extends CardBase {
    protected AreaTakingCard() {}

    protected AreaTakingCard(final Leader leader, final Rarity rarity, final CardClass defaultCardClass, final int defaultCost) {
        super(leader, rarity, defaultCardClass, defaultCost);
    }

    protected void initEffects() {}

    public abstract void comesIntoPlay();
    public abstract List<EffectBase> collectEffects();

    protected boolean hasEffect(final Predicate<? super EffectBase> filter) {
        return collectEffects().stream().anyMatch(filter);
    }

    @Override
    public void play(final List<Choice> options) {
        // TODO: Wordwielder Ginger
        for (final EffectBase effect: this.collectEffects()) {
            if (effect instanceof Fanfare) {
                ((Fanfare) effect).setOptions(options);
                this.getGame().pushEffect(effect);
            }
        }
        super.play(options);
        comesIntoPlay();
    }

    public void destroy() throws GameEndingException {
        // Ghost
        // Ghosthound Sexton
        if (this.hasEffect(effect -> effect instanceof LeavingIsBanishing)
                && !this.hasEffect(effect -> effect instanceof BanishingIsDestroy)) {
            banish();
            return;
        }

        removeFromArea();
        destroyed();
    }

    public void banish() {
        removeFromArea();
        banished();
    }

    protected void banished() {
        assert leader != null;
        leader.report(Report.builder().type(Report.ReportType.CARD_BANISHED).id(leader.getId())
                .name(getName()).build());
    }

    protected void destroyed() throws GameEndingException {
        assert leader != null;
        leader.report(Report.builder().type(Report.ReportType.CARD_DESTROYED).id(leader.getId())
                .name(getName()).build());
        leader.increaseShadow(1);
        for (final EffectBase effect: this.collectEffects()) {
            if (effect instanceof LastWords) {
                this.getGame().pushEffect(effect);
            }
        }
    }

    protected void removeFromArea() {
        assert leader != null;
        this.leader.removeCardFromArea(this);
        // TODO: leaving area effects
    }

    public boolean isInArea() {
        if (leader == null) return false;
        return leader.cardIsInArea(this);
    }

    public int getSelectPriority() {
        int priority = 0;
        for (final EffectBase effect: collectEffects()) {
            if (effect instanceof NotSelectable || effect instanceof Ambush) {
                return -1;
            }
            if (effect instanceof EffectWard) {
                priority = 1;
            }
        }
        return priority;
    }

    public boolean tryDestroy() {
        // TODO: not destroyable
        this.destroy();
        return true;
    }

    public boolean tryBanish() {
        // TODO: not able to be banished

        // Ghosthound Sexton
        if (hasEffect(effect -> effect instanceof BanishingIsDestroy)) {
            return this.tryDestroy();
        }

        this.banish();
        return true;
    }

    public void reset() {
        initEffects();
        super.reset();
    }

    protected void returnToHand() {
        this.removeFromArea();
        this.reset();
        this.getLeader().putCardIntoHand(this);
    }

    public boolean tryReturnToHand() {
        //TODO: cannot be returned to hand

        if (hasEffect(effect -> effect instanceof LeavingIsBanishing)) {
            if (this.tryBanish()) {
                return false;
            }
        }

        returnToHand();
        return true;
    }

    @Override
    public List<List<Choice>> getChoices() {
        // TODO: Ginger
        for (final EffectBase effect: collectEffects()) {
            if (effect instanceof Fanfare) {
                return ((Fanfare) effect).getChoices();
            }
        }
        return null;
    }
}
