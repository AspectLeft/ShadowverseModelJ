package model;

import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.neutral.Goblin;
import model.exception.GameEndingException;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;



class LeaderTest {
    @Mock
    public Game mockGame;

    public Collection<Class<? extends CardBase>> deck;

    Leader leader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setDeck();
        setLeader();
    }

    private void setDeck() {
        deck = new ArrayList<>();
        for (int i = 0; i < 40; ++i) {
            deck.add(Goblin.class);
        }
    }

    private void setLeader() {
        NoopReporter reporter = new NoopReporter();
        Mockito.when(mockGame.getReporter()).thenReturn(reporter);
        leader = new Leader(mockGame, 0, CardClass.FORESTCRAFT, true, deck);
    }

    @Test
    void removeCardsFromDeck() {
       assertEquals(3, leader.removeCardsFromDeck(3).size());
    }

    @Test
    void removeCardsFromDeck_LargeCount() {
        assertEquals(40, leader.removeCardsFromDeck(41).size());
    }

    @Test
    void removeCardsFromDeck_IllegalArgument() {
        assertThrows(AssertionError.class, () -> leader.removeCardsFromDeck(-1));
    }

    @Test
    void changeCardsOnStart() throws GameEndingException {
        leader.drawCardsOnStart(3);
        leader.changeCardsOnStart(Arrays.asList(true, false, true));
        assertEquals(3, leader.getHandSize());
    }

    @Test
    void startTurn() {
        assertDoesNotThrow(() -> leader.startTurn());
        assertEquals(1, leader.getMaximumPp());
        assertEquals(1, leader.getPp());
        assertEquals(1, leader.getHandSize());
    }

    @Test
    void startTurn_11th() {
        assertDoesNotThrow(() -> {
            for (int t = 1; t < 11; ++t) {
                leader.startTurn();
            }
        });
        assertEquals(10, leader.getMaximumPp());
        assertEquals(10, leader.getPp());
        assertFullHand();
    }

    @Test
    void drawCard_3_4_5_6() {
        assertDoesNotThrow(() -> {
            leader.drawCard(3);
            assertEquals(3, leader.getHandSize());
            leader.drawCard(4);
            leader.drawCard(5);
            assertFullHand();
            assertEquals(3, leader.getCountOfShadow());
            leader.drawCard(6);
            assertFullHand();
            assertEquals(9, leader.getCountOfShadow());
        });
    }

    @Test
    void drawCard_41() {
        assertThrows(GameEndingException.class, () -> leader.drawCard(41));
    }

    private void assertFullHand() {
        assertEquals(Leader.MAXIMUM_HAND_CARD, leader.getHandSize());
    }
}