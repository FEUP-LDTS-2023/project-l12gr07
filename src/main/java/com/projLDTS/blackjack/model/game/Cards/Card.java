package com.projLDTS.blackjack.model.game.Cards;

public record Card(String suit, String rank) {
    public int getValue() {
        return switch (rank) {
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "Jack", "Queen", "King" -> 10;
            default -> 11; // Ace is 11 initially
        };
    }
}
