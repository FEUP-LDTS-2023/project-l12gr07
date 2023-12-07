package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Decks.Deck;
import com.projLDTS.blackjack.viewer.game.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController implements StateController {
    private ApplicationStateController applicationStateController;
    private Deck deck;

    public GameController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        // por acabar

        while (true) {
            int aux = userInput();
            if (aux == 4) {
                nextState();
                break;
            }
            else setButtonSelected(aux);
            applicationStateController.redraw();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        applicationStateController.changeState(ApplicationState.MainMenu);
    }

    @Override
    public int getButtonSelected() {
        return applicationStateController.getButtonSelected();
    }

    @Override
    public void setButtonSelected(int i) {
        applicationStateController.setButtonSelected(i);
    }

    @Override
    public int userInput() throws IOException {
        return applicationStateController.userInput();
    }

    public void setDeck(Deck deck_) {
        deck = deck_;
    }
}
