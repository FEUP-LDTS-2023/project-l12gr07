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

    public void hit() {
        // boolean split?
        player.hit(deck, true);
    }

    public void stand() {
        int playerHand = player.stand();
        int op = dealer.stand(playerHand, deck);
        if (op == 0) player.setCredit(player.getCredit() + 1); // TODO: trocar 1 por 2x aposta do player
        if (op == 2) player.setCredit(player.getCredit() + 1); // TODO: trocar 1 por 1x aposta do player
        if (player.getCredit() == 0) {}// TODO: gameOver
        else nextGame();
    }

    public void split() {
        player.split(deck);
    }

    public void doubledown() {
        // boolean split?
        player.doubleDown(deck, true);
    }
}
