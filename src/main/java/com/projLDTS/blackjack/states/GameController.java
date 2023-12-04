package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.game.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController implements StateController {
    private ApplicationStateController applicationStateController;
    private GameViewer gameViewer;
    private final LanternaGUI gui;
    public GameController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
        gui = new LanternaGUI(130, 40);
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        // por acabar

        gameViewer = new GameViewer(gui);
        gameViewer.draw();
        while (true) {
            int aux = new UserInput(gui).MainMenuInput(gameViewer.getButtonSelected());
            if (aux == 3) {
                nextState();
                break;
            }
            else gameViewer.setButtonSelected(aux);
            gameViewer.drawElements();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        gui.close();
        applicationStateController.changeState(ApplicationState.MainMenu);
    }
}
