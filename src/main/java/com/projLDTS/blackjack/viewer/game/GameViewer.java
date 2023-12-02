package com.projLDTS.blackjack.viewer.game;

import com.projLDTS.blackjack.gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameViewer {
    private final LanternaGUI gui;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private boolean gameOver;
    public GameViewer(LanternaGUI gui_) {
        gui = gui_;
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        gameOver = false;
    }
    class Card {
        private final String suit;
        private final String rank;
        public Card(String suit, String rank) {
            this.suit = suit;
            this.rank = rank;
        }
        public String getSuit() {
            return suit;
        }
        public String getRank() {
            return rank;
        }
    }
    public void dealInitialHands() {
        // for the player
        drawCard(playerHand);
        drawCard(playerHand);
        // for the dealer
        drawCard(dealerHand);
        drawCard(dealerHand);
    }
    // player's decision to draw a card
    public void playerHit() {
        drawCard(playerHand);
        checkBust(playerHand);
    }
    // player's decision to end turn
    public void playerStand() {
        // dealer draws cards until their hand value is lower than 17
        while (calculateHand(dealerHand) < 17) { drawCard(dealerHand); }
        determineWinner();
        gameOver = true;
    }
    private void drawDealerHand(LanternaGUI gui_) throws IOException {
        List<Card> deck = new ArrayList<Card>();
        if (deck.size() < 2) {
            deck.add(new Card("", ""));
        }
        // por acabar

    }
    private void drawPlayerHand(LanternaGUI gui_) throws IOException {
        List<Card> deck = new ArrayList<Card>();
        // por acabar
    }
    private void drawCard(List<Card> hand) {
        // por acabar
    }
    // ace as 1 or 11
    private int calculateHand(List<Card> hand) {
        // por acabar
        return 0;
    }
    // bust = 21
    private void checkBust(List<Card> hand) {
        // por acabar
    }
    private void determineWinner() {
        // por acabar
    }
    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public boolean isGameOver() {
        return gameOver;
    }

}
