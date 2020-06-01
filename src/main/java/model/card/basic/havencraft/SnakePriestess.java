package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Ward;

public class SnakePriestess extends Follower {
    public SnakePriestess(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 2,
                1, 3,
                3, 5);
    }

    @Override
    protected void initEffects() {
        final Ward ward = new Ward(this);
        unevolvedEffects.add(ward);
        evolvedEffects.add(ward);
    }

    @Override
    public String getName() {
        return "Snake Priestess";
    }
}
