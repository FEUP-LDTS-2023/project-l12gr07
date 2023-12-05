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
    private final LanternaGUI gui;
    public Last10GamesMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
        gui = new LanternaGUI(130, 40);
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        last10GamesMenuViewer = new Last10GamesMenuViewer(gui);
        last10GamesMenuViewer.draw();
        while (true) {
            int aux = new UserInput(gui).Last10GamesMenuInput(
                    last10GamesMenuViewer.getButtonSelected());
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
            gui.close();
        }
    }
}
