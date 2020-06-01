package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.portalcraft.token.Puppet;
import model.effect.Bane;
import model.effect.Evolve;
import model.exception.GameEndingException;
import util.RandomUtil;

import java.util.stream.Collectors;

public final class Puppeteer extends Follower {
    public Puppeteer(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initEffects() {
        Evolve evolve = new Evolve(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.putCardIntoHand(new Puppet(leader));

                final Puppet puppet = (Puppet) RandomUtil.pickOne(leader.getRandom(), leader.getHand().stream()
                        .filter(card -> card instanceof Puppet).collect(Collectors.toList()));
                if (puppet != null) {
                    puppet.attachEffect(new Bane(puppet));
                }
            }
        };
        evolvedEffects.add(evolve);
    }

    @Override
    public String getName() {
        return "Puppeteer";
    }
}
