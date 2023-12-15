package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Decks.Deck;

public class Player extends CardSet {
    private static int credit = UserInput.getCredit();
    private final Hand splitHand;
    public Player() {
        super();
        credit =  UserInput.getCredit();
        splitHand = new Hand();
    }

    public Hand getSplitHand() { return splitHand; }
    public boolean hit(Deck deck) {
        Hand currentHand = hand;
        if (currentHand.getValue() < 21) {
            System.out.println(currentHand.getValue());
            currentHand.addCard(deck);
        }
        if (currentHand.getValue() > 21) return false;
        return true;
    }
    public int stand() {
        return hand.getValue();
    }
    public boolean doubleDown(Deck deck) {
        Hand currentHand = hand;
        if (currentHand.getValue() >= 21 ||
            2 * currentHand.getBet() > credit) {
            return false;
        }
        currentHand.addCard(deck);
        credit = credit - currentHand.getBet();
        currentHand.setBet(currentHand.getBet() * 2);
        return true;
    }

    public void clearHand() {
        for (int i = 0; i < hand.getHand().size(); i++) {
            hand.removeCard(i);
        }
        for (int i = 0; i < splitHand.getHand().size(); i++) {
            hand.removeCard(i);
        }
    }
}
