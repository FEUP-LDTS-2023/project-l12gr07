package com.projLDTS.blackjack.model.game.Decks;

import com.projLDTS.blackjack.model.game.Cards.Card;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Deck {
    private final ArrayList<Card> deck;
    private boolean infinite = false;
    public Deck(int n) {
        deck = new ArrayList<>();
        if (n == 0) {
            infinite = true;
            n = 1;
        }
        for (int i = 0; i < n; i++) {
            for (String suit : new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}) {
                deck.add(new Card(suit, "2"));
                deck.add(new Card(suit, "3"));
                deck.add(new Card(suit, "4"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "6"));
                deck.add(new Card(suit, "7"));
                deck.add(new Card(suit, "8"));
                deck.add(new Card(suit, "9"));
                deck.add(new Card(suit, "J"));
                deck.add(new Card(suit, "Q"));
                deck.add(new Card(suit, "K"));
                deck.add(new Card(suit, "A"));
            }
        }
    }
    public boolean isInfinite() {
        return infinite;
    }
    public List<Card> getDeck() {
        return deck;
    }
    public void randomize() {
        Random random = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }
}
