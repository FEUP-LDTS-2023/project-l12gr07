package com.projLDTS.blackjack.model.game.Cards;

public class CardSet {
    public Hand hand;
    public CardSet() {
        hand = new Hand();
    }
    public Hand getHand() {
        return hand;
    }
}
