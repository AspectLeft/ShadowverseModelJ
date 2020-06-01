package model.card.basic.swordcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Follower;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickbladerTest {

    Game game;

    Leader leader;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> fourQs = Arrays.asList(
                Quickblader.class, Quickblader.class, Quickblader.class, Quickblader.class);

        game = new Game(1, CardClass.SWORDCRAFT, fourQs,
                2, CardClass.SWORDCRAFT, fourQs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
    }

    @Test
    void storm() {
        leader.playCardFromHand(0, null);
        Follower follower = (Follower) leader.getArea()[0];
        assertTrue(follower.canAttackFollower());
        assertTrue(follower.canAttackLeader());
    }
}