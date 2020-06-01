package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.shadowcraft.token.Zombie;
import model.effect.LastWords;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class UndeadKing extends Follower {
    public UndeadKing(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.SHADOWCRAFT, 7,
                4, 4,
                6, 6);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = owner.getLeader();
                leader.summon((Arrays.asList(new Zombie(leader), new Zombie(leader))));
            }
        };
        unevolvedEffects.add(lastWords);
        evolvedEffects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Undead King";
    }
}
