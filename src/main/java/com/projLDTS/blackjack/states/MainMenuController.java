package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.MainMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuController implements StateController {
    private ApplicationStateController applicationStateController;
    private MainMenuViewer mainMenuViewer;
    private final LanternaGUI gui;

    public MainMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
        gui = new LanternaGUI(130, 40);
    }
    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        mainMenuViewer = new MainMenuViewer(gui);
        mainMenuViewer.draw();
        while (true) {
            int aux = new UserInput(gui).MainMenuInput(mainMenuViewer.getButtonSelected());
            if (aux == 3) {
                nextState();
                break;
            }
            else mainMenuViewer.setButtonSelected(aux);
            mainMenuViewer.drawElements();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        if (mainMenuViewer.getButtonSelected() == 0) applicationStateController.changeState(ApplicationState.StartMenu);
        else if (mainMenuViewer.getButtonSelected() == 1) applicationStateController.changeState(ApplicationState.HowToPlay);
        else if (mainMenuViewer.getButtonSelected() == 2) applicationStateController.changeState(ApplicationState.Exit);
        gui.close();
    }
}
