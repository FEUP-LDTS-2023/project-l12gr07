package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StartMenuController implements StateController {
    private final ApplicationStateController applicationStateController;

    public StartMenuController(ApplicationStateController applicationStateController_) {
        applicationStateController = applicationStateController_;

    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        while (true) {
            int aux = userInput();
            if (aux == 3) {
                nextState();
                break;
            }
            else setButtonSelected(aux);
            applicationStateController.redraw();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        if (getButtonSelected() == 0) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
            applicationStateController.changeState(ApplicationState.MainMenu);
        }
        else if (getButtonSelected() == 1) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
            applicationStateController.changeState(ApplicationState.DecksMenu);
        }
        else if (getButtonSelected() == 2) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
            applicationStateController.changeState(ApplicationState.Last10Games);
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
