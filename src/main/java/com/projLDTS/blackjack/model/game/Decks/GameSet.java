package com.projLDTS.blackjack.model.game.Decks;

import com.projLDTS.blackjack.model.game.Cards.Player;
import com.projLDTS.blackjack.model.game.Cards.Dealer;

public class GameSet {
    private final Player player;
    private final Dealer dealer;
    private Deck deck;
    private static GameSet game = null;

    public GameSet() {
        super();
        deck = new Deck(1); // to do : ir buscar aos decks
        deck.randomize();
        player = new Player();
        dealer = new Dealer();
    }

    public static GameSet getGame() {
        if (game == null) {
            game = new GameSet();
        }
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setNDecks(int n) {
        deck = new Deck(n);
    } // ir buscar ao outro menu

    public void dealCards() {
        player.getHand().addCard(deck);
        player.getHand().addCard(deck);
        dealer.getHand().addCard(deck);
    }

    public void nextGame() {
        // to do
    }

    public void winningRounds() {
        // to do
    }
}
