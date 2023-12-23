package com.projLDTSblackjack.model.game.Decks;

import com.projLDTS.blackjack.model.game.Cards.Card;
import com.projLDTS.blackjack.model.game.Cards.Hand;
import com.projLDTS.blackjack.model.game.Cards.Player;
import com.projLDTS.blackjack.model.game.Cards.Dealer;
import com.projLDTS.blackjack.gui.UserInput;

import com.projLDTS.blackjack.model.game.Decks.Deck;
import com.projLDTS.blackjack.model.game.Decks.GameSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameSetTest {
    private Deck mockDeck;
    private UserInput mockUserInput;

    private GameSet gameSet;

    @BeforeEach
    void setUp() {
        mockDeck = mock(Deck.class);
        gameSet = new GameSet(1);
        gameSet.setDeck(mockDeck);
    }

    @Test
    void testInitialization() {
        assertNotNull(gameSet.getPlayer());
        assertNotNull(gameSet.getDealer());
        assertNotNull(gameSet.getDeck());
    }
    @Test
    void testDealingCards() {
        int initialPlayerHandSize = gameSet.getPlayer().getHand().getHand().size();
        int initialDealerHandSize = gameSet.getDealer().getHand().getHand().size();
        gameSet.dealCards();
        assertEquals(initialPlayerHandSize + 2, gameSet.getPlayer().getHand().getHand().size());
        assertEquals(initialDealerHandSize + 1, gameSet.getDealer().getHand().getHand().size());
    }

    @Test
    void testHit() {
        when(mockDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        int initialPlayerHandSize = gameSet.getPlayer().getHand().getHand().size();
        assertTrue(gameSet.hit());
        int finalPlayerHandSize = gameSet.getPlayer().getHand().getHand().size();
        assertEquals(initialPlayerHandSize + 1, finalPlayerHandSize);
    }

    @Test
    void testStand() {
        // TODO
    }

    @Test
    void testDoubledown() {
        // TODO
    }

    @Test
    void testNextGame() {
        gameSet.getPlayer().getHand().setBet(10);
        when(mockDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        doNothing().when(mockDeck).randomize();
        gameSet.nextGame();
        assertEquals(0, gameSet.getPlayer().getHand().getBet());
        assertEquals(1, gameSet.getDealer().getHand().getHand().size());
        assertEquals(2, gameSet.getPlayer().getHand().getHand().size());
    }

    private List<Card> createMockDeck(int numCards, String suit) {
        List<Card> mockDeck = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            mockDeck.add(new Card(suit, "2"));
        }
        return mockDeck;
    }
}
