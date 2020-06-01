package model.card.basic.runecraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.forestcraft.token.Fairy;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MagicMissileTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        final List<Class<? extends CardBase>> fourWFs = Arrays.asList(
                WaterFairy.class, WaterFairy.class, WaterFairy.class, WaterFairy.class);

        final List<Class<? extends CardBase>> tenMMs = Collections.nCopies(10, MagicMissile.class);

        game = new Game(1, CardClass.FORESTCRAFT, fourWFs,
                2, CardClass.RUNECRAFT, tenMMs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void effect_follower() {
        WaterFairy waterFairy = (WaterFairy) leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(2);
        assertEquals(5, opponent.getHandSize());
        assertEquals(5, opponent.getDeckSize());
        opponent.playCardFromHand(0, Collections.singletonList(opponent
                .getEnemyEffectTargetFollowerAndLeaderChoices().get(0)));
        assertEquals(0, waterFairy.getDefense());
        assertEquals(1, leader.getCountOfShadow());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
        assertNull(leader.getArea()[0]);
        assertEquals(5, opponent.getHandSize());
    }

    @Test
    void effect_leader() {
        leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(2);
        opponent.playCardFromHand(0, Collections.singletonList(opponent
                .getEnemyEffectTargetFollowerAndLeaderChoices().get(1)));
        assertEquals(19, leader.getDefense());
    }
}