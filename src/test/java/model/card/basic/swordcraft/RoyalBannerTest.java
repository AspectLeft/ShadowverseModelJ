package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoyalBannerTest {

    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Quickblader.class, Quickblader.class).getCurrentLeader();
    }

    @Test
    void effects() {
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);
        leader.playCard(new RoyalBanner(leader), null);
        leader.playCardFromHand(0, null);
        leader.getAlliedFollowers().forEach(follower -> assertEquals(2, follower.getAttack()));
    }
}