package model.card.basic.havencraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.havencraft.token.HolywingDragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurateTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Curate.class, Curate.class).getCurrentLeader();
    }

    @Test
    void fanfare_leader() {
        leader.setDefense(15);
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, leader.getHand().get(0).getChoices().get(0));
        assertEquals(20, leader.getDefense());
    }

    @Test
    void fanfare_follower() {
        HolywingDragon holywingDragon = new HolywingDragon(leader);
        leader.summon(holywingDragon);
        holywingDragon.takeDamage(5);
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, leader.getHand().get(0).getChoices().get(0));
        assertEquals(6, holywingDragon.getDefense());
    }

}