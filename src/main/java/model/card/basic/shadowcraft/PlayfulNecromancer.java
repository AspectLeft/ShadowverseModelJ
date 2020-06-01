package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.shadowcraft.token.Ghost;
import model.effect.Evolve;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class PlayfulNecromancer extends Follower {
    public PlayfulNecromancer(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.SHADOWCRAFT, 4,
                4, 3,
                5, 4);
    }

    @Override
    protected void initEffects() {
        final Evolve evolve = new Evolve(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.summon(Arrays.asList(new Ghost(leader), new Ghost(leader)));
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Playful Necromancer";
    }
}
