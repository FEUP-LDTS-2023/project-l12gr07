package com.projLDTS.blackjack.viewer.game;

import com.projLDTS.blackjack.gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameViewer {
    private final LanternaGUI gui;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private boolean gameOver;
    int buttonSelected;
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    public GameViewer(LanternaGUI gui_) {
        gui = gui_;
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        gameOver = false;
    }

    public void draw() throws IOException {
        gui.clear();
        gui.drawCredit();
        gui.drawLine();
        gui.drawBet();
        gui.refresh();
    }

    public int getButtonSelected() {
        return buttonSelected;
    }
    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    public void drawElements() throws IOException {
        gui.drawHitButton(false);
        gui.drawStandButton(false);
        gui.drawDoubleDownButton(false);
        gui.drawSplitButton(false);
        if (buttonSelected == 0) gui.drawHitButton(true);
        else if (buttonSelected == 1) gui.drawStandButton(true);
        else if (buttonSelected == 2) gui.drawDoubleDownButton(true);
        else if (buttonSelected == 3) gui.drawSplitButton(true);
        gui.refresh();
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
        // Draw the first card face up
        Card firstCard = dealerHand.get(0);
        gui_.drawCard(firstCard.getSuit(), firstCard.getRank());
        // Draw the second card face down
        gui_.drawHiddenCard();

    }

    private void drawPlayerHand(LanternaGUI gui_) throws IOException {
        for (Card card : playerHand) {
            drawCard(card.getSuit(), card.getRank());
        }
    }

    private String drawCard(String suit, String rank) {
        return rank + " " + suit;
        // por acabar
    }

    private void drawCard(List<Card> hand) {
        Card card = generateRandomCard();
        hand.add(card);
    }

    private Card generateRandomCard() {
        Random random = new Random();
        String suit = SUITS[random.nextInt(SUITS.length)];
        String rank = RANKS[random.nextInt(RANKS.length)];
        return new Card(suit, rank);
    }
    // ace as 1 or 11
    private int calculateHand(List<Card> hand) {
        int sum = 0;
        int numAces = 0;
        for (Card card : hand) {
            String rank = card.getRank();
            if (rank.equals("Ace")) {
                numAces++;
                sum += 11; // Ace is 11 initially
            } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
                sum += 10;
            } else {
                sum += Integer.parseInt(rank);
            }
        }
        while (numAces > 0 && sum > 21) {
            sum -= 10; // change Ace to 1
            numAces--;
        }
        return sum;
    }
    // bust = 21
    private void checkBust(List<Card> hand) {
        if (calculateHand(hand) > 21) {
            gameOver = true;
            if (hand == playerHand) {
                System.out.println("Player got busted! Dealer is the winner!");
            } else {
                System.out.println("Dealer got busted! Player is the winner!");
                // em falta : ir buscar username do player
            }
        }
    }
    private void determineWinner() {
        int playerScore = calculateHand(playerHand);
        int dealerScore = calculateHand(dealerHand);
        // em falta : ir buscar username do player
        if (playerScore > dealerScore) {
            System.out.println("Player is the winner!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer is the winner!");
        } else {
            System.out.println("It's a tie!");
        }
        gameOver = true;
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
