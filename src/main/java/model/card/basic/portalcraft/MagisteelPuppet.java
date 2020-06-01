package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.portalcraft.token.Puppet;
import model.effect.Evolve;
import model.exception.GameEndingException;

import java.util.Arrays;

public class MagisteelPuppet extends Follower {
    public MagisteelPuppet(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.PORTALCRAFT, 2,
                2, 2,
                3, 3);
    }

    @Override
    protected void initEffects() {
        Evolve evolve = new Evolve(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.putCardsIntoHand(Arrays.asList(new Puppet(leader), new Puppet(leader)));
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Magisteel Puppet";
    }
}
