package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class DecksMenuController implements StateController {
    private ApplicationStateController applicationStateController;
    public DecksMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        while (true) {
            int aux = userInput();
            if (aux == 4) {
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
            //One Deck game
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
            applicationStateController.changeState(ApplicationState.Game);
            GameController gameController = (GameController) applicationStateController.getStateController();
            gameController.setGameType(1);
        }
        else if (getButtonSelected() == 1) {
            //Two Deck game
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
            applicationStateController.changeState(ApplicationState.Game);
            GameController gameController = (GameController) applicationStateController.getStateController();
            gameController.setGameType(2);
        }
        else if (getButtonSelected() == 2) {
            //Inf Deck game
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
            applicationStateController.changeState(ApplicationState.Game);
            GameController gameController = (GameController) applicationStateController.getStateController();
            gameController.setGameType(0);
        }
        else if (getButtonSelected() == 3) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
            applicationStateController.changeState(ApplicationState.MainMenu);
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
