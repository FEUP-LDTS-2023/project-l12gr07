package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.Viewer;
import com.projLDTS.blackjack.viewer.menus.MainMenuViewer;
import com.projLDTS.blackjack.viewer.menus.StartMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StartMenuController implements StateController {
    private ApplicationStateController applicationStateController;
    private StartMenuViewer startMenuViewer;
    private final LanternaGUI gui;

    public StartMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
        gui = new LanternaGUI(130, 40);
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        startMenuViewer = new StartMenuViewer(gui);
        startMenuViewer.draw();
        while (true) {
            int aux = new UserInput(gui).StartMenuInput(startMenuViewer.getButtonSelected());
            if (aux == 3) {
                nextState();
                break;
            }
            else startMenuViewer.setButtonSelected(aux);
            startMenuViewer.drawElements();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        gui.close();
        if (startMenuViewer.getButtonSelected() == 1) applicationStateController.changeState(ApplicationState.MainMenu);
        else if (startMenuViewer.getButtonSelected() == 0) applicationStateController.changeState(ApplicationState.Game);
        else if (startMenuViewer.getButtonSelected() == 2) applicationStateController.changeState(ApplicationState.LastPlays);
    }
}
