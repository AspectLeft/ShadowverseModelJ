package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.Choice;
import model.effect.Fanfare;
import model.effect.IncrementalBuff;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public class WhiteGeneral extends Follower {
    public WhiteGeneral(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 4,
                3, 3,
                5, 5);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.COMMANDER);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public List<List<Choice>> getChoices() {
                return Collections.singletonList(owner.getLeader().getAreaChoicesWithFilter(card ->
                        card instanceof Follower && card.hasTrait(Trait.OFFICER)));
            }

            @Override
            public void settle() throws GameEndingException {
                if (options == null || options.isEmpty()) {
                    return;
                }
                final Follower target = (Follower) owner.getLeader().getArea()[options.get(0).getValue()];
                if (!target.isInArea()) return;
                report(null);
                final IncrementalBuff buff = new IncrementalBuff(target, 2, 0);
                target.attachEffect(buff);
                buff.settle();
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "White General";
    }
}
