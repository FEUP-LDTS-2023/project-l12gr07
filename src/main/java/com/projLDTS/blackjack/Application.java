package com.projLDTS.blackjack;

import com.projLDTS.blackjack.Menus.MainMenu;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            new MainMenu().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}