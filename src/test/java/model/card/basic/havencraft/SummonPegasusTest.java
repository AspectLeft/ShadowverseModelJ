package model.card.basic.havencraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.havencraft.token.Pegasus;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SummonPegasusTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> fourtySPs = Collections.nCopies(40, SummonPegasus.class);

        game = new Game(1, CardClass.HAVENCRAFT, fourtySPs,
                2, CardClass.HAVENCRAFT, fourtySPs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void lastWords() {
        //Turn 1
        assertEquals(4, leader.getHandSize());
        assertEquals(0, leader.getCountOfShadow());
        leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.endTurn();

        //Turn 2
        assertEquals(4, leader.getHandSize());
        assertEquals(0, leader.getCountOfShadow());
        leader.endTurn();
        opponent.endTurn();

        //Turn 3
        assertEquals(5, leader.getHandSize());
        assertEquals(0, leader.getCountOfShadow());
        leader.endTurn();
        opponent.endTurn();

        //Turn 4
        assertEquals(6, leader.getHandSize());
        assertEquals(0, leader.getCountOfShadow());
        leader.endTurn();
        opponent.endTurn();

        //Turn 5
        assertEquals(7, leader.getHandSize());
        assertEquals(1, leader.getCountOfShadow());
        assertTrue(leader.getArea()[0] instanceof Pegasus);
    }

}