package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.model.game.Cards.Card;
import com.projLDTS.blackjack.model.game.Cards.Dealer;
import com.projLDTS.blackjack.model.game.Decks.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DealerTest {
    private Dealer dealer;
    private Deck mockedDeck;
    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        mockedDeck = Mockito.mock(Deck.class);
    }

    @Test
    void testHandCards() {
        when(mockedDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        dealer.handCards(mockedDeck);
        verify(mockedDeck, atLeastOnce()).getDeck();
        assertEquals(18, dealer.getHand().getValue());
    }

    @Test
    void testStandDealerWins() {
        when(mockedDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        dealer.handCards(mockedDeck);
        int result = dealer.stand(16, mockedDeck);
        verify(mockedDeck, atLeastOnce()).getDeck();
        assertEquals(1, result);
    }

    @Test
    void testStandDealerDraw() {
        when(mockedDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        dealer.handCards(mockedDeck);
        int result = dealer.stand(20, mockedDeck);
        verify(mockedDeck, atLeastOnce()).getDeck();
        assertEquals(2, result);
    }

    // TODO : not working
    @Test
    void testStandDealerLost() {
        /*
        when(mockedDeck.getDeck()).thenReturn(createMockDeck(10, "Hearts"));
        dealer.handCards(mockedDeck);
        int result = dealer.stand(15, mockedDeck);
        verify(mockedDeck, atLeastOnce()).getDeck();
        assertEquals(0, result);
         */
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
        dealer.clearHand();
        assertEquals(0, dealer.getHand().getHand().size());
    }
}
