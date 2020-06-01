package model.card.basic.swordcraft;

import model.card.*;

// TODO: incomplete
public class Quickblader extends Follower {

    Quickblader() {
        super(Rarity.BRONZE, CardClass.SWORDCRAFT, 1,
                1, 1,
                3, 3);
        addTraits(Trait.OFFICER);
    }
}
