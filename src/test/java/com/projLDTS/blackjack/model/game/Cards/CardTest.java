package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.model.game.Cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("Hearts", "9");
    }

    @Test
    void testGetSuit() {
        String suit = card.getSuit();
        assertEquals("Hearts", suit);
    }

    @Test
    void testGetValue() {
        int value = card.getValue();
        assertEquals(9, value);
    }

    @Test
    void testGetSuitString() {
        String suitString = card.getSuitString("Hearts");
        assertEquals("{", suitString);
    }

    @Test
    void testGetRankString() {
        String rankString = card.getRankString();
        assertEquals("9", rankString);
    }
    @Test
    void testGetSuitForHearts() {
        card = new Card("Hearts", "8");
        String suit = card.getSuit();
        assertEquals("Hearts", suit);
    }

    @Test
    void testGetSuitForDiamonds() {
        card = new Card("Diamonds", "8");
        String suit = card.getSuit();
        assertEquals("Diamonds", suit);
    }

    @Test
    void testGetSuitForClubs() {
        card = new Card("Clubs", "8");
        String suit = card.getSuit();
        assertEquals("Clubs", suit);
    }

    @Test
    void testGetSuitForSpades() {
        card = new Card("Spades", "8");
        String suit = card.getSuit();
        assertEquals("Spades", suit);
    }
    @Test
    void testGetValueForTwo() {
        card = new Card("Clubs", "2");
        int value = card.getValue();
        assertEquals(2, value);
    }

    @Test
    void testGetValueForThree() {
        card = new Card("Hearts", "3");
        int value = card.getValue();
        assertEquals(3, value);
    }

    @Test
    void testGetValueForFour() {
        card = new Card("Diamonds", "4");
        int value = card.getValue();
        assertEquals(4, value);
    }

    @Test
    void testGetValueForFive() {
        card = new Card("Spades", "5");
        int value = card.getValue();
        assertEquals(5, value);
    }

    @Test
    void testGetValueForSix() {
        card = new Card("Hearts", "6");
        int value = card.getValue();
        assertEquals(6, value);
    }

    @Test
    void testGetValueForSeven() {
        card = new Card("Clubs", "7");
        int value = card.getValue();
        assertEquals(7, value);
    }

    @Test
    void testGetValueForEight() {
        card = new Card("Diamonds", "8");
        int value = card.getValue();
        assertEquals(8, value);
    }

    @Test
    void testGetValueForNine() {
        card = new Card("Spades", "9");
        int value = card.getValue();
        assertEquals(9, value);
    }

    @Test
    void testGetValueForTen() {
        card = new Card("Hearts", "10");
        int value = card.getValue();
        assertEquals(10, value);
    }

    @Test
    void testGetValueForJack() {
        card = new Card("Diamonds", "J");
        int value = card.getValue();
        assertEquals(10, value);
    }

    @Test
    void testGetValueForQueen() {
        card = new Card("Spades", "Q");
        int value = card.getValue();
        assertEquals(10, value);
    }

    @Test
    void testGetValueForKing() {
        card = new Card("Clubs", "K");
        int value = card.getValue();
        assertEquals(10, value);
    }

    @Test
    void testGetValueForAce() {
        card = new Card("Hearts", "Ace");
        int value = card.getValue();
        assertEquals(11, value);
    }
    @Test
    void testGetValueInvalidRank() {
        card = new Card("Hearts", "InvalidRank");
        int value = card.getValue();
        assertEquals(11, value);
    }

    @Test
    void testGetValueInvalidSuit() {
        card = new Card("InvalidSuit", "9");
        int value = card.getValue();
        assertEquals(9, value);
    }

    @Test
    void testGetPlayingCard() {
        List<String> expectedPlayingCard = new ArrayList<>();
        expectedPlayingCard.add("+---------+");
        expectedPlayingCard.add("|9        |");
        expectedPlayingCard.add("|         |");
        expectedPlayingCard.add("|         |");
        expectedPlayingCard.add("|    {    |");
        expectedPlayingCard.add("|         |");
        expectedPlayingCard.add("|         |");
        expectedPlayingCard.add("|        9|");
        expectedPlayingCard.add("+---------+");

        assertEquals(expectedPlayingCard, card.getPlayingCard());
    }
}
