package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.Fanfare;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public class ElfMetallurgist extends Follower {
    private Fanfare fanfare;

    public ElfMetallurgist(Leader leader) {
        super(leader, Rarity.SILVER, CardClass.FORESTCRAFT, 2,
                2, 1,
                4, 3);
    }

    @Override
    protected void initEffects() {
        fanfare = new Fanfare(this) {
            @Override
            public List<List<Choice>> getChoices() {
                if (owner.getLeader().getCountOfCardsPlayedThisTurn() < 2) return null;
                return Collections.singletonList(owner.getLeader().getEnemyEffectTargetFollowerChoices());
            }

            @Override
            public void settle() throws GameEndingException {
                if (options != null && !options.isEmpty()) {
                    report(null);
                    final Follower target = (Follower) owner.getLeader().getOpponent().getArea()
                            [options.get(0).getValue()];
                    getOwner().effectDamage(2, target);
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public void play(final List<Choice> options) {
        fanfare.setOptions(options);
        super.play(options);
    }

    @Override
    public String getName() {
        return "Elf Metallurgist";
    }
}
