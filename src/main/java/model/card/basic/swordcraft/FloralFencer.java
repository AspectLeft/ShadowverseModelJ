package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.card.basic.swordcraft.token.Knight;
import model.card.basic.swordcraft.token.SteelcladKnight;
import model.effect.Evolve;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class FloralFencer extends Follower {
    public FloralFencer(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.SWORDCRAFT, 4,
                3, 4,
                4, 5);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    protected void initEffects() {
        final Evolve summon2 = new Evolve(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.summon(Arrays.asList(new SteelcladKnight(leader), new Knight(leader)));
            }
        };
        evolvedEffects.add(summon2);
    }

    @Override
    public String getName() {
        return "Floral Fencer";
    }
}
