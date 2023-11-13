package com.projLDTS.blackjack;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            new Start().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}