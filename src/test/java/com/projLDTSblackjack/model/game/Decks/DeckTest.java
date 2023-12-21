package com.projLDTSblackjack.model.game.Decks;

import com.projLDTS.blackjack.model.game.Cards.Card;
import com.projLDTS.blackjack.model.game.Decks.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import java.util.Random;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DeckTest {
    private Card mockCard;
    private RandomGenerator randomGenerator;

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck(1);
    }
    @Test
    public void testDeckInitialization() {
        int numberOfDecks = 2;
        Deck deck = new Deck(numberOfDecks);
        assertEquals(numberOfDecks * 52, deck.getDeck().size());
    }

    @Test
    public void testDeckIsInfinite() {
        int infiniteDecks = 0;
        Deck deck = new Deck(infiniteDecks);
        assertTrue(deck.isInfinite());
    }

    @Test
    public void testDeckIsNotInfinite() {
        int numberOfDecks = 3;
        Deck deck = new Deck(numberOfDecks);
        assertFalse(deck.isInfinite());
    }

    @Test
    public void testDeckContainsCards() {
        List<Card> cards = deck.getDeck();
        assertFalse(cards.isEmpty());
        assertEquals(52, cards.size());
    }


}
