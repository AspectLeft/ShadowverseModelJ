package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.Spellboost;
import model.effect.Choice;
import model.effect.cost.SpellboostDecreaseCost;
import model.report.Report;

import java.util.List;

public final class FlameDestroyer extends Follower implements Spellboost {
    private int spellboost = 0;

    public FlameDestroyer(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.RUNECRAFT, 10,
                7, 7,
                9, 9);
        costOperators.add(new SpellboostDecreaseCost(this));
    }

    @Override
    public String getName() {
        return "Flame Destroyer";
    }

    @Override
    public void play(List<Choice> options) {
        spellboost = 0;
        super.play(options);
    }

    @Override
    public void increaseSpellboost(final int delta) {
        this.spellboost += delta;
        getLeader().report(Report.builder().type(Report.ReportType.SPELL_BOOST).id(getLeader().getId())
                .name(getName()).values(new int[]{delta}).build());
    }

    @Override
    public int getSpellboost() {
        return spellboost;
    }

    @Override
    public void reset() {
        super.reset();
        this.spellboost = 0;
    }
}
