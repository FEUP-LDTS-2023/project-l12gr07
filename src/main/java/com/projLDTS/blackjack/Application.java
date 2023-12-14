package com.projLDTS.blackjack;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) {
        try {
            ApplicationStateController applicationStateController = new ApplicationStateController();
            applicationStateController.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}