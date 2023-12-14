package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.model.game.Decks.Deck;

public class Player extends CardSet {
    private static int credit;
    private final Hand splitHand;
    public Player() {
        super();
        credit = 1000; // começa com 1000
        splitHand = new Hand();
    }
    public static int getCredit() { return credit; }
    public static void setCredit(int credit) { Player.credit = credit; }
    public Hand getSplitHand() { return splitHand; }
    public boolean hit(Deck deck, boolean split) { // TODO: split hand
        Hand currentHand = hand;
        if (currentHand.getValue() < 21) {
            currentHand.addCard(deck);
            return true;
        }
        return false;
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
