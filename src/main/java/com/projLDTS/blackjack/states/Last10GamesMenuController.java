package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Last10GamesMenuController implements StateController {
    private ApplicationStateController applicationStateController;

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
            else applicationStateController.getStateViewer().setButtonSelected(aux);
            applicationStateController.getStateViewer().drawElements();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        if (applicationStateController.getStateViewer().getButtonSelected() == 0) {
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
