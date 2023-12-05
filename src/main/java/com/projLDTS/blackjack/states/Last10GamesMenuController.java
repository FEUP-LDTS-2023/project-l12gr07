package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.Last10GamesMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Last10GamesMenuController implements StateController {
    private ApplicationStateController applicationStateController;
    private Last10GamesMenuViewer last10GamesMenuViewer;
    public Last10GamesMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        while (true) {
            int aux = userInput();
            if (aux == 1) {
                nextState();
                break;
            }
            else last10GamesMenuViewer.setButtonSelected(aux);
            last10GamesMenuViewer.drawElements();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        if (last10GamesMenuViewer.getButtonSelected() == 0) {
            applicationStateController.changeState(ApplicationState.StartMenu);
        }
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
}
