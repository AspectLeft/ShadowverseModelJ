package model.card.basic.portalcraft.token;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzingArtifactTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(AnalyzingArtifact.class, AnalyzingArtifact.class).getCurrentLeader();
    }

    @Test
    void lastWords() {
        AnalyzingArtifact artifact = (AnalyzingArtifact) leader.playCardFromHand(0, null);
        artifact.destroy();
        leader.getGame().settleAllEffects();
        assertEquals(4, leader.getHandSize());
    }
}