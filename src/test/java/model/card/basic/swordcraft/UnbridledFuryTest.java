package model.card.basic.swordcraft;

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

class UnbridledFuryTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        final List<Class<? extends CardBase>> fourWFs = Arrays.asList(
                WaterFairy.class, WaterFairy.class, WaterFairy.class, WaterFairy.class);

        final List<Class<? extends CardBase>> fiveUFs = Collections.nCopies(5, UnbridledFury.class);

        game = new Game(1, CardClass.FORESTCRAFT, fourWFs,
                2, CardClass.SWORDCRAFT, fiveUFs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void effect() {
        WaterFairy waterFairy = (WaterFairy)leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(3);
        opponent.putCardIntoHand(new OathlessKnight(opponent));
        opponent.playCardFromHand(opponent.getHandSize() - 1, null);
        opponent.playCardFromHand(0, opponent.getEnemyEffectTargetFollowerChoices());
        assertEquals(-1, waterFairy.getDefense());
        assertEquals(1, leader.getCountOfShadow());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
        assertNull(leader.getArea()[0]);
    }

}