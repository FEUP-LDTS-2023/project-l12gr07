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
        if (UserInput.getBet().toString().isEmpty()){
            bet = 0;
        } else {
            bet =  Integer.parseInt(UserInput.getBet().toString()); // transforma stringBuilder to String and then to int
        }

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
    public boolean checkBust() {
        return getValue() > 21;
    }
    public boolean checkBlackjack() {
        return (hand.size() == 2 && getValue() == 21);
    }
    public int getBet() {
        return bet;
    }
    public void setBet(int bet) {
        int totalCredit = UserInput.getCredit();
        UserInput.setCredit(totalCredit - bet);
    }

    public void removeCard(int i) {
        hand.remove(i);
    }
}
