package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.card.spell.Spellboost;
import model.effect.Choice;
import model.effect.ChoiceType;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;
import model.report.Report;

import java.util.List;

public class WindBlast extends EnemyFollowerTargetingSpell implements Spellboost {
    private int spellboost = 0;

    public WindBlast(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = (Leader) getOwner();
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                leader.spellDamage(1 + options.get(1).getValue(), target);
            }
        });
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
        return "Wind Blast";
    }
}
