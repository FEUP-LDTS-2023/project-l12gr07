package com.projLDTS.blackjack.model.game.Cards;

import java.util.ArrayList;

public class BaseCard {
    ArrayList<String> playingCard = new ArrayList<>();
    String rankString;
    String suit;
    public BaseCard(int rank, String suit){
        switch (suit) {
            case "Hearts":
                this.suit = "♥";
                break;
            case "Diamonds":
                this.suit = "♦";
                break;
            case "Clubs":
                this.suit = "♣";
                break;
            case "Spades":
                this.suit = "♠";
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
        // Add card content to the playingCard list
        playingCard.add("+---------+");
        playingCard.add("|" + rankString + "        |");
        playingCard.add("|         |");
        playingCard.add("|         |");
        playingCard.add("|    " + this.suit + "    |");
        playingCard.add("|         |");
        playingCard.add("|         |");
        playingCard.add("|       " + rankString + " |");
        playingCard.add("+----------+");
    }

    public ArrayList<String> getPlayingCard() {
        return playingCard;
    }
    public String getRank() {
        return rankString;
    }

    public String getSuit() {
        return suit;
    }

}
