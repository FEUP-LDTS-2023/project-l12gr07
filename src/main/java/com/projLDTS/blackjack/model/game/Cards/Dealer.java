package com.projLDTS.blackjack.model.game.Cards;
import com.projLDTS.blackjack.model.game.Decks.Deck;

public class Dealer extends CardSet {
    public Dealer() {
        super();
    }

    public int stand(int playerHand, Deck deck) {
        hand.addCard(deck);
        if (hand.getValue() > 21) return 0; // dealer lost
        else if (hand.getValue() > playerHand && hand.getValue() <= 21) return 1; // dealer won
        else if (hand.getValue() == playerHand) return 2; // draw
        return 3; // another card
    }

    public void clearHand() {
        hand.getHand().clear();
    }
}
