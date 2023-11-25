package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.Viewer;
import com.projLDTS.blackjack.viewer.menus.ExitMenuViewer;
import com.projLDTS.blackjack.viewer.menus.StartMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ExitMenuController implements StateController {
    private ApplicationStateController applicationStateController;
    private ExitMenuViewer exitMenuViewer;
    private final LanternaGUI gui;
    public ExitMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
        gui = new LanternaGUI(130, 40);
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        exitMenuViewer = new ExitMenuViewer(gui);
        exitMenuViewer.draw();
        while (true) {
            int aux = new UserInput(gui).ExitAndHowToPlayMenuInput();
            if (aux == 2) {
                nextState();
                break;
            }
            else exitMenuViewer.setButtonSelected(aux);
            exitMenuViewer.drawElements();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        if (exitMenuViewer.getButtonSelected() == 0) gui.close();
        else if (exitMenuViewer.getButtonSelected() == 1) {
            applicationStateController.changeState(ApplicationState.MainMenu);
            gui.close();
        }
    }
}
