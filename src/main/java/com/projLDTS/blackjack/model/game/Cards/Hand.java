package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.model.game.Decks.Deck;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> hand;
    private int bet;
    public Hand() {
        hand = new ArrayList<>();
        bet = 0;
    }
    public List<Card> getHand() {
        return hand;
    }
    public void addCard(Deck deck) {
        List<Card> deckList = deck.getDeck();
        if (!deckList.isEmpty()) {
            Card drawnCard = deckList.get(deckList.size() - 1);
            hand.add(drawnCard);
            deckList.remove(deckList.size() - 1);
        }
    }
    public void addCard(Card card) {
        hand.add(card);
    }
    public int getValue() {
        int sum = 0;
        int numAces = 0;
        for (Card card : hand) {
            int cardValue = card.getValue();
            if (cardValue == 11) {
                numAces++;
            }
            sum += cardValue;
        }
        while (numAces > 0 && sum > 21) {
            sum -= 10; // change Ace to 1
            numAces--;
        }
        return sum;
    }
    public boolean checkBust() {
        return getValue() > 21;
    }
    public boolean checkBlackjack() {
        return (hand.size() == 2 && getValue() == 21);
    }
    public int getBet() {
        return bet;
    }
    public void setBet() {
    }
}
