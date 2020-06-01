package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import model.card.basic.shadowcraft.token.Lich;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HellsUnleasherTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(HellsUnleasher.class, HellsUnleasher.class).getCurrentLeader();
    }

    @Test
    void lastWords() {
        leader.setMaximumPpAndRecover(4);
        Follower follower = (Follower) leader.playCardFromHand(0, null);
        follower.destroy();
        leader.getGame().settleAllEffects();
        assertTrue(leader.getArea()[0] instanceof Lich);
    }

}