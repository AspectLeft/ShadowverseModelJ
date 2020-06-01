package model.card.basic.neutral;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

//TODO: incomplete
public final class Goblin extends Follower {

    public Goblin(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.NEUTRAL, 1,
                1, 2,
                3, 4);
    }

    @Override
    public String getName() {
        return "Goblin";
    }
}
