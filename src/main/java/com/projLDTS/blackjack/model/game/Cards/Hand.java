package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Decks.Deck;
import com.projLDTS.blackjack.model.game.Decks.GameSet;
import com.projLDTS.blackjack.viewer.game.GameViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {
    private final List<Card> hand;
    private int bet;
    public Hand() {
        hand = new ArrayList<>();
        bet =  UserInput.getintBet();
    }
    public List<Card> getHand() {
        return hand;
    }
    public void addCard(Deck deck) {
        if (deck.isInfinite())
            deck.randomize();
        List<Card> deckList = deck.getDeck();
        if (!deckList.isEmpty()) {
            Card drawnCard = deckList.get(deckList.size() - 1);
            hand.add(drawnCard);
            if (!deck.isInfinite())
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
            if (Objects.equals(card.getRankString(), "A")) {
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
    public int getBet() {
        bet = UserInput.getintBet();
        return bet;
    }

    public void setBet(int b) {
        bet = b;
    }

    public void removeCard(int i) {
        hand.remove(i);
    }
}
