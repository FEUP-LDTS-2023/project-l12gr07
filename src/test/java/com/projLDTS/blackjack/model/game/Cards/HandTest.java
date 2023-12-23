package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Cards.Card;
import com.projLDTS.blackjack.model.game.Cards.Hand;
import com.projLDTS.blackjack.model.game.Decks.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class HandTest {
    private Hand hand;
    private Deck mockedDeck;

    @BeforeEach
    void setUp() {
        hand = new Hand();
        mockedDeck = Mockito.mock(Deck.class);
    }

    @Test
    void testAddCardFromDeck() {
        when(mockedDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        hand.addCard(mockedDeck);
        assertEquals(1, hand.getHand().size());
    }

    private List<Card> createMockDeck(int numCards, String suit) {
        List<Card> mockDeck = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            mockDeck.add(new Card(suit, "2"));
        }
        return mockDeck;
    }

}
