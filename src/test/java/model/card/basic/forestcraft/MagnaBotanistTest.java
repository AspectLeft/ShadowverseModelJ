package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import model.card.basic.forestcraft.token.Fairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagnaBotanistTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Fairy.class, Fairy.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(8);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        leader.playCard(new MagnaBotanist(leader), null);

        for (final Follower follower: leader.getAlliedFollowers()) {
            if (follower instanceof Fairy) {
                assertEquals(2, follower.getAttack());
                assertEquals(2, follower.getDefense());
            }
            else {
                assertEquals(6, follower.getAttack());
                assertEquals(6, follower.getDefense());
            }
        }
    }
}