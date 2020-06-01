package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.Spellboost;
import model.effect.Choice;
import model.effect.ChoiceType;
import model.effect.Fanfare;
import model.exception.GameEndingException;
import model.report.Report;

import java.util.Collections;
import java.util.List;

public final class LightningShooter extends Follower implements Spellboost {
    private int spellboost = 0;

    public LightningShooter(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.RUNECRAFT, 5,
                3, 3,
                5, 5);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public List<List<Choice>> getChoices() {
                return Collections.singletonList(owner.getLeader().getEnemyEffectTargetFollowerChoices());
            }

            @Override
            public void settle() throws GameEndingException {
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                owner.effectDamage(1 + options.get(1).getValue(), target);
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public void play(List<Choice> options) {
        options.add(Choice.builder().type(ChoiceType.SPELLBOOST).value(spellboost).build());
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
    @Override
    public String getName() {
        return "Lightning Shooter";
    }
}
