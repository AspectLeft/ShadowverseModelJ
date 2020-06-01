package model.card.basic.neutral;

import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

//TODO: in complete
public class Goblin extends Follower {

    Goblin() {
        super(Rarity.BRONZE, CardClass.NEUTRAL, 1,
                1, 2,
                3, 4);
    }
}
