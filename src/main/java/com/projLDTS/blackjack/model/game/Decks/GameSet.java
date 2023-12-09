package com.projLDTS.blackjack.model.game.Decks;

import com.projLDTS.blackjack.model.game.Cards.Player;
import com.projLDTS.blackjack.model.game.Cards.Dealer;

public class GameSet {
    private final Player player;
    private final Dealer dealer;
    private Deck deck;
    private static GameSet game = null;

    public GameSet(int n) {
        super();
        deck = new Deck(n); // TODO : ir buscar aos decks
        deck.randomize();
        player = new Player();
        dealer = new Dealer();
    }

    public static GameSet getGame() {
        if (game == null) {
            game = new GameSet(1);
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

    public void dealCards() {
        player.getHand().addCard(deck);
        player.getHand().addCard(deck);
        dealer.getHand().addCard(deck);
    }

    public void nextGame() {
        // TODO
    }

    public void winningRounds() {
        // TODO
    }

    public boolean hit() {
        // TODO: boolean split?
        return player.hit(deck, true);
    }

    public void stand() {
        player.stand();
    }

    public boolean split() {
        return player.split(deck);
    }

    public boolean doubledown() {
        // TODO: boolean split?
        return player.doubleDown(deck, true);
    }
}
