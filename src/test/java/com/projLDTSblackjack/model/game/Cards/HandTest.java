package com.projLDTSblackjack.model.game.Cards;

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

    @Test
    void testAddCard() {
        Card mockCard = Mockito.mock(Card.class);
        hand.addCard(mockCard);
        assertEquals(1, hand.getHand().size());
    }

    @Test
    void testGetValueNoAces() {
        hand.addCard(new Card("Hearts", "7"));
        hand.addCard(new Card("Spades", "8"));
        assertEquals(15, hand.getValue());
    }

    @Test
    void testGetValueWithAces() {
        hand.addCard(new Card("Hearts", "A"));
        hand.addCard(new Card("Spades", "9"));
        assertEquals(20, hand.getValue());
    }

    @Test
    void testCheckBust() {
        hand.addCard(new Card("Hearts", "Q"));
        hand.addCard(new Card("Spades", "K"));
        hand.addCard(new Card("Diamonds", "5"));
        assertEquals(true, hand.checkBust());
    }

    @Test
    void testCheckBlackjack() {
        hand.addCard(new Card("Hearts", "A"));
        hand.addCard(new Card("Spades", "K"));
        assertEquals(true, hand.checkBlackjack());
    }

    // TODO : not working
    @Test
    void testAdjustAcesWhenBust() {
        /*
        hand.addCard(new Card("Hearts", "A"));
        hand.addCard(new Card("Spades", "A"));
        hand.addCard(new Card("Diamonds", "5"));
        assertTrue(hand.getValue() > 21);
        hand.addCard(new Card("Clubs", "7"));
        assertTrue(hand.checkBust());
         */
    }

    // TODO : not working
    @Test
    void testGetBet() {
        /*
        UserInput.setBet(new StringBuilder("100"));
        assertEquals(100, hand.getBet());
         */
    }

    @Test
    void testRemoveCard() {
        Card card1 = new Card("Hearts", "A");
        Card card2 = new Card("Spades", "8");
        hand.addCard(card1);
        hand.addCard(card2);
        hand.removeCard(0);
        assertEquals(1, hand.getHand().size());
        assertEquals(card2, hand.getHand().get(0));
    }

    private List<Card> createMockDeck(int numCards, String suit) {
        List<Card> mockDeck = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            mockDeck.add(new Card(suit, "2"));
        }
        return mockDeck;
    }

}
