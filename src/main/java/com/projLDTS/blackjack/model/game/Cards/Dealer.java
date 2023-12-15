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
        hand.addCard(deck);
        System.out.println("B" + hand.getValue());
        if (hand.getValue() > 21) return 0; // dealer lost
        else if (hand.getValue() == playerHand) return 2; // draw
        else if (hand.getValue() > playerHand && hand.getValue() <= 21) return 1; // dealer won
        return 3; // another card
    }

    public void clearHand() {
        for (int i = 0; i < hand.getHand().size(); i++) {
            hand.removeCard(i);
        }
    }
}
