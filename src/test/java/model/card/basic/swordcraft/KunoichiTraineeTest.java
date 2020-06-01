package model.card.basic.swordcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KunoichiTraineeTest {
    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> tenKTs = Collections.nCopies(10, KunoichiTrainee.class);

        game = new Game(1, CardClass.SWORDCRAFT, tenKTs,
                2, CardClass.SWORDCRAFT, tenKTs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void ambush() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        assertEquals(0, leader.getEffectTargetFollowerIndices().size());
        assertEquals(0, leader.getStrikeTargetIndices().size());

        leader.endTurn();

        opponent.endTurn();

        leader.followerStrike(0, opponent);
        assertEquals(0, leader.getEffectTargetFollowerIndices().get(0));
        assertEquals(0, leader.getStrikeTargetIndices().get(0));
        assertEquals(0, leader.getArea()[0].collectEffects().size());
    }



}