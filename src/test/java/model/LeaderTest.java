package model;

import model.card.CardBase;
import model.card.CardClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;



class LeaderTest {
    @Mock
    public Collection<CardBase> mockDeck;

    Leader leader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setMockDeck();
        setLeader();
    }

    private void setMockDeck() {
        CardBase[] mockCards = new CardBase[40];
        for (int i = 0; i < 40; ++i) {
            mockCards[i] = mock(CardBase.class);
        }
        when(mockDeck.toArray()).thenReturn(mockCards);
    }

    private void setLeader() {
        leader = new Leader(0, CardClass.FORESTCRAFT, true, mockDeck);
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
        assertThrows(AssertionError.class, () -> {
            leader.removeCardsFromDeck(-1);
        });
    }
}