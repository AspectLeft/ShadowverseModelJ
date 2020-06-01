package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.card.spell.Spellboost;
import model.effect.Choice;
import model.effect.EffectWithOptions;
import model.effect.cost.SpellboostDecreaseCost;
import model.exception.GameEndingException;
import model.report.Report;

import java.util.List;

public final class FieryEmbrace extends EnemyFollowerTargetingSpell implements Spellboost {
    private int spellboost = 0;

    public FieryEmbrace(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 8, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().getOpponent().getArea()[options.get(0).getValue()].tryDestroy();
            }
        });

        costOperators.add(new SpellboostDecreaseCost(this));
    }

    @Override
    public void play(List<Choice> options) {
        spellboost = 0;
        super.play(options);
    }

    @Override
    public String getName() {
        return "Fiery Embrace";
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
