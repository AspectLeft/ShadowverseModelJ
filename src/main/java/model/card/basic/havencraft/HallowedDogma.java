package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.amulet.CountdownAmulet;
import model.card.spell.Spell;
import model.effect.Choice;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public class HallowedDogma extends Spell {
    public HallowedDogma(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final CountdownAmulet amulet = (CountdownAmulet) owner.getLeader().getArea()[options.get(0).getValue()];
                amulet.decreaseCount(2, true);
                owner.getLeader().drawCard(1);
            }
        });
    }

    @Override
    public void play(final List<Choice> options) throws GameEndingException {
        assert effect != null;
        ((EffectWithOptions) effect).setOptions(options);
        super.play(options);
    }

    @Override
    public List<List<Choice>> getChoices() {
        return Collections.singletonList(getLeader().getCountdownAmuletChoices());
    }

    @Override
    public String getName() {
        return "Hallowed Dogma";
    }
}
