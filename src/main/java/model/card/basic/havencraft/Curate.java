package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.ChoiceType;
import model.effect.Fanfare;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public final class Curate extends Follower {
    public Curate(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.HAVENCRAFT, 7,
                5, 5,
                7, 7);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public List<List<Choice>> getChoices() {
                final List<Choice> healTargets = owner.getLeader().getAlliedFollowerChoices();
                healTargets.add(Choice.builder().type(ChoiceType.SELF_AREA_LEADER).value(-1).build());
                return Collections.singletonList(healTargets);
            }

            @Override
            public void settle() throws GameEndingException {
                if (options == null || options.isEmpty()) return;
                report(null);
                final int targetIndex = options.get(0).getValue();
                final int healValue = 5;
                if (targetIndex == -1) {
                    owner.getLeader().heal(healValue);
                }
                else {
                    ((Follower)(owner.getLeader().getArea()[targetIndex])).heal(healValue);
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Curate";
    }
}
