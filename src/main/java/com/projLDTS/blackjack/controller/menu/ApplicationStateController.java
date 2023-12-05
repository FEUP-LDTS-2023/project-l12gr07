package com.projLDTS.blackjack.controller.menu;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.states.*;
import com.projLDTS.blackjack.viewer.StateViewer;
import com.projLDTS.blackjack.viewer.menus.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ApplicationStateController {
    private StateController stateController;
    private StateViewer stateViewer;
    private ApplicationState applicationState;
    private final LanternaGUI gui;

    public ApplicationStateController() throws IOException, FontFormatException, URISyntaxException {
        gui = new LanternaGUI(130, 40);
        changeState(ApplicationState.MainMenu);
        stateViewer = new MainMenuViewer(gui);
    }

    public StateController getStateController() {
        return stateController;
    }

    public StateViewer getStateViewer(){return stateViewer;}

    public void run() throws IOException, FontFormatException, URISyntaxException {
        while (stateController != null) {
            stateViewer.draw();
            stateController.run();
        }
    }

    public void redraw() throws IOException {
        stateViewer.draw();
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void changeState(ApplicationState state) throws IOException, FontFormatException, URISyntaxException {
        applicationState = state;
        switch (state) {
            case Game:
                //stateController = new GameController(this);
                break;

            case MainMenu:
                stateController = new MainMenuController(this);
                stateViewer = new MainMenuViewer(gui);
                break;

            case HowToPlay:
                stateController = new HowToPlayMenuController(this);
                stateViewer = new HowToPlayMenuViewer(gui);
                break;

            case StartMenu:
                stateController = new StartMenuController(this);
                stateViewer = new StartMenuViewer(gui);
                break;

            case DecksMenu:
                stateController = new DecksMenuController(this);
                stateViewer = new DecksMenuViewer(gui);
                break;

            case Last10Games:
                stateController = new Last10GamesMenuController(this);
                break;

//            case GameOver:
//                stateController = new GameOverController(this);
//                break;

            case Exit:
                stateController = new ExitMenuController(this);
                stateViewer = new ExitMenuViewer(gui);
        }
    }

    public void close() throws IOException {
        stateViewer.close();
    }

    public int userInput() throws IOException {
        return stateViewer.userInput();
    }

    public void setButtonSelected(int i) {
        stateViewer.setButtonSelected(i);
    }

    public int getButtonSelected() {
        return stateViewer.getButtonSelected();
    }
}
