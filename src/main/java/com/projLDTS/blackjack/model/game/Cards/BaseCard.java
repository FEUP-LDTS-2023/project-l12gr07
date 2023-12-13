package com.projLDTS.blackjack.model.game.Cards;

import java.util.ArrayList;

public class BaseCard {
    ArrayList<String> playingCard = new ArrayList<>();
    public BaseCard(int rank, String suit){
        String rankString;
        switch (suit) {
            case "Hearts":
                suit = "♥";
                break;
            case "Diamonds":
                suit = "♦";
                break;
            case "Clubs":
                suit = "♣";
                break;
            case "Spades":
                suit = "♠";
                break;
        }
        switch (rank) {
            case 11:
                rankString = "J";
                break;
            case 12:
                rankString = "Q";
                break;
            case 13:
                rankString = "K";
                break;
            case 1:
                rankString = "A";
                break;
            default:
                rankString = Integer.toString(rank);
                break;
        }
        playingCard.add("┌─────────┐");
        playingCard.add("│" + rankString + "        │");
        playingCard.add("│         │");
        playingCard.add("│         │");
        playingCard.add("│    " + suit + "    │");
        playingCard.add("│         │");
        playingCard.add("│         │");
        playingCard.add("│       " + rankString + " │");
        playingCard.add("└─────────┘");
    }
}
