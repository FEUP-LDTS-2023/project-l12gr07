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
}
