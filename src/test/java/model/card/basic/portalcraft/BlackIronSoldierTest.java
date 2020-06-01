package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.portalcraft.token.AnalyzingArtifact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BlackIronSoldierTest {

    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(BlackIronSoldier.class, BlackIronSoldier.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.putCardsIntoDeck(Collections.singletonList(new AnalyzingArtifact(leader)));
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);
        assertTrue(leader.getHand().get(3) instanceof AnalyzingArtifact);
    }

}