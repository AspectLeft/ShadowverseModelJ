package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.shadowcraft.token.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApprenticeNecromancerTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(ApprenticeNecromancer.class, ApprenticeNecromancer.class)
                .getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.increaseShadow(4);
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, null);
        assertNotNull(leader.getArea()[1]);
        assertTrue(leader.getArea()[1] instanceof Zombie);
        assertEquals(0, leader.getCountOfShadow());
    }
}