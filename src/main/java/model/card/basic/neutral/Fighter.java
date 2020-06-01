package model.card.basic.neutral;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public class Fighter extends Follower {
    protected Fighter(Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.NEUTRAL, 2,
                2, 2,
                4, 4);
    }

    @Override
    public String getName() {
        return "Fighter";
    }
}
