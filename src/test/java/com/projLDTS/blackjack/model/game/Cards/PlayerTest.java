package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Cards.Card;
import com.projLDTS.blackjack.model.game.Cards.Player;
import com.projLDTS.blackjack.model.game.Decks.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.*;

public class PlayerTest {
    private Player player;
    private Deck mockedDeck;

    @BeforeEach
    void setUp() {
        player = new Player();
        mockedDeck = Mockito.mock(Deck.class);
    }

    @Test
    void testHitBelow21() {
        player.getHand().setBet(10);
        when(mockedDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        player.hit(mockedDeck);
        verify(mockedDeck, atLeastOnce()).getDeck();
        assertTrue(player.getHand().getValue() <= 21);
        assertFalse(player.getHand().getHand().isEmpty());
    }

    @Test
    void testStand() {
        player.getHand().addCard(new Card("Hearts", "7"));
        player.getHand().addCard(new Card("Spades", "8"));
        int result = player.stand();
        assertEquals(15, result);
    }

    private List<Card> createMockDeck(int numCards, String suit) {
        List<Card> mockDeck = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            mockDeck.add(new Card(suit, "2"));
        }
        return mockDeck;
    }

    @Test
    void testClearHand() {
        player.getHand().addCard(new Card("Hearts", "7"));
        player.getHand().addCard(new Card("Spades", "8"));
        player.clearHand();
        assertTrue(player.getHand().getHand().isEmpty());
    }
    @Test
    void testDoubleDown() {
        // TODO
    }

}
