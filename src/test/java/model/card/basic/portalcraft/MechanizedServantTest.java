package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MechanizedServantTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(MechanizedServant.class, MechanizedServant.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(2);
        final Follower follower = (Follower) leader.playCardFromHand(0, null);
        assertTrue(follower.canAttackFollower());
    }
}