package com.projLDTSblackjack.model.game.Cards;

import com.projLDTS.blackjack.model.game.Cards.Card;
import com.projLDTS.blackjack.model.game.Cards.CardSet;
import com.projLDTS.blackjack.model.game.Cards.Hand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardSetTest {
    private CardSet cardSet;
    private org.mockito.Mockito Mockito;

    @BeforeEach
    void setUp() {
        cardSet = new CardSet();
    }

    @Test
    void testCardSetInitialization() {
        Assertions.assertNotNull(cardSet);
        Assertions.assertNotNull(cardSet.getHand());
    }

    @Test
    void testGetHand() {
        Hand hand = cardSet.getHand();
        Assertions.assertNotNull(hand);
    }

    @Test
    void testGetHandSameInstance() {
        Hand hand1 = cardSet.getHand();
        Hand hand2 = cardSet.getHand();
        Assertions.assertSame(hand1, hand2);
    }

}
