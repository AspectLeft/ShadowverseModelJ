package model.card.basic.neutral;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Follower;
import model.card.basic.forestcraft.WaterFairy;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WellOfDestinyTest {
    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> deck = Arrays.asList(
                WaterFairy.class, WaterFairy.class, WaterFairy.class,
                WellOfDestiny.class, WellOfDestiny.class, WellOfDestiny.class
                );

        game = new Game(1, CardClass.FORESTCRAFT, deck,
                2, CardClass.FORESTCRAFT, deck,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    private void playCardClass(Leader leader, Class<? extends CardBase> cardClass) {
        for (int i = 0; i < leader.getHandSize(); ++i) {
            if (cardClass.isInstance(leader.getHand().get(i))) {
                leader.playCardFromHand(i, null);
                break;
            }
        }
    }

    @Test
    void effect() {
        leader.setMaximumPpAndRecover(3);
        playCardClass(leader, WaterFairy.class);
        playCardClass(leader, WellOfDestiny.class);
        leader.endTurn();

        opponent.endTurn();
        assertTrue(leader.getArea()[1] instanceof WellOfDestiny);
        assertEquals(2, ((Follower)(leader.getArea()[0])).getAttack());
    }
}