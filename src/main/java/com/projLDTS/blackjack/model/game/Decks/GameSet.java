package com.projLDTS.blackjack.model.game.Decks;

import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Cards.Player;
import com.projLDTS.blackjack.model.game.Cards.Dealer;

public class GameSet {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private static GameSet game = null;
    int type;

    public GameSet(int n) {
        super();
        type = n;
        deck = new Deck(n);
        deck.randomize();
        player = new Player();
        dealer = new Dealer();
        dealCards();
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
        player.getHand().getBet();

        deck = new Deck(type);
        deck.randomize();
        dealer.clearHand();
        player.clearHand();
        dealCards();
    }

    public boolean hit() {
        return player.hit(deck);
    }

    public int stand() {
        int playerHand = player.stand();
        int op = dealer.stand(playerHand, deck);
        if (op == 0) { // Player Won
            UserInput.setCredit(UserInput.getCredit() + 2 * player.getHand().getBet());
            return 1;
        }
        if (op == 2) { // Draw
            UserInput.setCredit(UserInput.getCredit() + player.getHand().getBet());
            return 2;
        }
        if (op == 1) { // Dealer Won
            return 0;
        }
        return 3; // another card
    }

    public boolean doubledown() {
        return player.doubleDown(deck);
    }

    public void setDeck(Deck mockDeck) {
        this.deck = deck;
    }
}
