package com.projLDTS.blackjack.model.game.Cards;
import com.projLDTS.blackjack.model.game.Decks.Deck;

public class Dealer extends CardSet {
    public Dealer() {
        super();
    }
    public void handCards(Deck deck) {
        while (hand.getValue() < 17) {
            hand.addCard(deck);
        }
    }

    public int stand(int playerHand, Deck deck) {
        while (hand.getValue() < 21 || hand.getValue() < playerHand) {
            hand.addCard(deck);
            if (hand.getValue() > 21) {
                return 0; // dealer lost
            }
        }
        if (hand.getValue() == playerHand) return 2; // draw
        return 1; // dealer won
    }
}
