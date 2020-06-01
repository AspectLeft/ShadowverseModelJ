package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Bane;

public class Spectre extends Follower {
    public Spectre(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 2,
                2, 1,
                4, 3);
    }

    @Override
    protected void initEffects() {
        final Bane bane = new Bane(this);
        unevolvedEffects.add(bane);
        evolvedEffects.add(bane);
    }

    @Override
    public String getName() {
        return "Spectre";
    }
}
