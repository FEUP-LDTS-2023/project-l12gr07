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
    public boolean hit(Deck deck, boolean split) { // TODO: split hand
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
    public boolean doubleDown(Deck deck, boolean split) { // TODO: split hand
        Hand currentHand = hand;
        if (currentHand.getHand().size() != 2 ||
            currentHand.getValue() >= 21 ||
            currentHand.getBet() > credit) {
            return false;
        }
        currentHand.addCard(deck);
        credit = credit - currentHand.getBet();
        currentHand.setBet(currentHand.getBet() * 2);
        return true;
    }
    public boolean split() {
        splitHand.addCard(hand.getHand().get(0));
        hand.removeCard(0);
        return true;
    }
    public boolean isSplit() {
        return !splitHand.getHand().isEmpty();
    }

    public boolean canSplit() {
        return hand.getHand().size() == 2 && hand.getHand().get(0).getValue() == hand.getHand().get(1).getValue();
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
