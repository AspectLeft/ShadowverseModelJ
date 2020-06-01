package model.card.basic.neutral;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public class MercenaryDrifter extends Follower {
    public MercenaryDrifter(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.NEUTRAL, 3,
                3, 2,
                5, 4);
    }

    @Override
    public String getName() {
        return "Mercenary Drifter";
    }
}
