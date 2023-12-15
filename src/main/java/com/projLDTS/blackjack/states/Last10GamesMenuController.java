package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Last10GamesMenuController implements StateController {
    private ApplicationStateController applicationStateController;

    public Last10GamesMenuController(ApplicationStateController applicationStateController_) {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        while (true) {
            int aux = userInput();
            if (aux == 0) {
                nextState();
                return;
            }
            else setButtonSelected(aux);
            applicationStateController.redraw();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        applicationStateController.changeState(ApplicationState.StartMenu);
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
