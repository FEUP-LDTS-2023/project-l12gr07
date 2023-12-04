package com.projLDTS.blackjack.controller.menu;

import com.projLDTS.blackjack.states.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ApplicationStateController {
    private StateController stateController;
    private ApplicationState applicationState;

    public ApplicationStateController() throws IOException, FontFormatException, URISyntaxException {
        changeState(ApplicationState.MainMenu);
    }

    public StateController getStateController() {
        return stateController;
    }

    public void run() throws IOException, FontFormatException, URISyntaxException {
        while (stateController != null) {
            stateController.run();
        }
    }


    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void changeState(ApplicationState state) throws IOException, FontFormatException, URISyntaxException {
        applicationState = state;
        switch (state) {
            case Game:
                stateController = new GameController(this);
                break;

            case MainMenu:
                stateController = new MainMenuController(this);
                break;

            case HowToPlay:
                stateController = new HowToPlayMenuController(this);
                break;

            case StartMenu:
                stateController = new StartMenuController(this);
                break;

//            case LastPlays:
//                stateController = new LastPlaysController(this);
//                break;

//            case GameOver:
//                stateController = new GameOverController(this);
//                break;

            case Exit:
                stateController = new ExitMenuController(this);
        }
    }
}
