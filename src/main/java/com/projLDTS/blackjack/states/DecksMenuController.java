package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.DecksMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class DecksMenuController implements StateController {
    private ApplicationStateController applicationStateController;
    private DecksMenuViewer decksMenuViewer;
    private final LanternaGUI gui;
    public DecksMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
        gui = new LanternaGUI(130, 40);
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        decksMenuViewer = new DecksMenuViewer(gui);
        decksMenuViewer.draw();
        while (true) {
            int aux = new UserInput(gui).DecksMenuInput(decksMenuViewer.getButtonSelected());
            if (aux == 4) {
                nextState();
                break;
            }
            else decksMenuViewer.setButtonSelected(aux);
            decksMenuViewer.drawElements();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        if (decksMenuViewer.getButtonSelected() == 0) {
            //One Deck game
            applicationStateController.changeState(ApplicationState.Game);
            gui.close();
        } else if (decksMenuViewer.getButtonSelected() == 1) {
            //Two Deck game
            applicationStateController.changeState(ApplicationState.Game);
            gui.close();
        }else if (decksMenuViewer.getButtonSelected() == 2) {
            //Inf Deck game
            applicationStateController.changeState(ApplicationState.Game);
            gui.close();
        } else if (decksMenuViewer.getButtonSelected() == 3) {
            applicationStateController.changeState(ApplicationState.MainMenu);
            gui.close();
        }
    }
}
