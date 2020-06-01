package model.card.basic.forestcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Follower;
import model.card.basic.forestcraft.token.Fairy;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WaterFairyTest {
    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> fourWFs = Arrays.asList(
                WaterFairy.class, WaterFairy.class, WaterFairy.class, WaterFairy.class);

        game = new Game(1, CardClass.FORESTCRAFT, fourWFs,
                2, CardClass.FORESTCRAFT, fourWFs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
    }

    @Test
    void lastWords() {
        leader.playCardFromHand(0, null);
        Follower waterFairy = (Follower)(leader.getArea()[0]);
        waterFairy.destroy();
        game.settleAllEffects();
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
    }

}