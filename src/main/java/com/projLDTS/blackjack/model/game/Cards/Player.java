package com.projLDTS.blackjack.model.game.Cards;

import com.projLDTS.blackjack.model.game.Decks.Deck;

public class Player extends CardSet {
    private int credit;
    private final Hand splitHand;
    public Player() {
        super();
        this.credit = 1000; // comeca com 1000? ou user define quanto quer apostar
        splitHand = new Hand();
    }
    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }
    public Hand getSplitHand() { return splitHand; }
    public boolean hit(Deck deck, boolean split) {
        Hand currentHand = split ? splitHand : hand;
        if (currentHand.getValue() < 21) {
            currentHand.addCard(deck);
            return true;
        }
        return false;
    }
    public void stand() {
        // TODO
    }
    public boolean doubleDown(Deck deck, boolean split) {
        // TODO
        return false;
    }
    public boolean split(Deck deck) {
        // TODO
        return false;
    }
    public boolean isSplit() {
        // TODO
        return false;
    }

}