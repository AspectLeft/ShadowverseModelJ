package model.card.basic.havencraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class Pegasus extends Follower {
    public Pegasus(Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 5,
                5, 3,
                7,5);
    }

    @Override
    public String getName() {
        return "Pegasus";
    }
}
