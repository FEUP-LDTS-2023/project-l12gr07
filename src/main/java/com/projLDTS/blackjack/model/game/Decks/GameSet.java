package com.projLDTS.blackjack.model.game.Decks;

import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Cards.Player;
import com.projLDTS.blackjack.model.game.Cards.Dealer;

public class GameSet {
    private final Player player;
    private final Dealer dealer;
    private Deck deck;
    private static GameSet game = null;
    int type;

    public GameSet(int n) {
        super();
        type = n;
        deck = new Deck(n); // TODO : ir buscar aos decks
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
        deck = new Deck(type);
        deck.randomize();
        dealer.clearHand();
        player.clearHand();
        dealCards();
    }

    public void winningRounds() {
        // TODO
    }

    public boolean hit() {
        return player.hit(deck, canSplit());
    }

    public int stand() {
        int playerHand = player.stand();
        int op = dealer.stand(playerHand, deck);
        if (op == 0) {
            UserInput.setCredit(UserInput.getCredit() + 2 * Integer.parseInt(UserInput.getBet().toString()));
            return 1;
        }
        if (op == 2) {
            UserInput.setCredit(UserInput.getCredit() + Integer.parseInt(UserInput.getBet().toString()));
            return 2;
        }
        if (UserInput.getCredit() == 0) {
            return 0;
        }
        return 3;
    }

    public boolean split() {
        return player.split();
    }

    public boolean doubledown() {
        return player.doubleDown(deck, canSplit());
    }

    public boolean canSplit() {
        return player.canSplit();
    }

}
