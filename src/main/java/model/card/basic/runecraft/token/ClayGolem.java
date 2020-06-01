package model.card.basic.runecraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class ClayGolem extends Follower {
    public ClayGolem(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    public String getName() {
        return "Clay Golem";
    }
}
