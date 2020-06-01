package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.runecraft.token.Snowman;
import model.card.spell.Spell;
import model.card.spell.Spellboost;
import model.effect.Choice;
import model.effect.ChoiceType;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;
import model.report.Report;
import util.CardCopyUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SummonSnow extends Spell implements Spellboost {
    private int spellboost = 0;

    public SummonSnow(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 3, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                // Avoid any pressure
                final int numberOfSnowman = Math.min(10, 1 + options.get(0).getValue());
                owner.getLeader().summon(Collections.nCopies(numberOfSnowman, Snowman.class).stream()
                        .map(cardClass -> (Follower) CardCopyUtil.createCardInstance(owner.getLeader(), cardClass))
                        .collect(Collectors.toList()));
            }
        });
    }

    @Override
    public void play(final List<Choice> options) {
        ((EffectWithOptions) effect).setOptions(Collections.singletonList(Choice.builder().type(ChoiceType.SPELLBOOST)
                .value(spellboost).build()));
        spellboost = 0;
        super.play(options);
    }

    @Override
    public String getName() {
        return "Summon Snow";
    }

    @Override
    public void increaseSpellboost(int delta) {
        this.spellboost += delta;
        getLeader().report(Report.builder().type(Report.ReportType.SPELL_BOOST).id(getLeader().getId())
                .name(getName()).values(new int[]{delta}).build());
    }

    @Override
    public int getSpellboost() {
        return spellboost;
    }
}
