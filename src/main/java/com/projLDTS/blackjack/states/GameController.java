package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Decks.Deck;
import com.projLDTS.blackjack.model.game.Decks.GameSet;
import com.projLDTS.blackjack.viewer.game.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController implements StateController {
    private ApplicationStateController applicationStateController;
    private GameSet gameSet;
    private int gameType;

    public GameController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        // TODO: POR ACABAR
        gameSet = new GameSet(gameType);
        while (true) {
            int aux = userInput();
            if (aux == 5) {
                nextState();
                break;
            }
            else if (aux == 4) {
                play();
            }
            else {
                setButtonSelected(aux);
            }
            applicationStateController.redraw();
        }
    }

    private void play() {
        if (getButtonSelected() == 0) {
            boolean aux = gameSet.hit();
        }
        else if (getButtonSelected() == 1) {
            gameSet.stand();
        }
        else if (getButtonSelected() == 2) {
            boolean aux = gameSet.doubledown();
        }
        else if (getButtonSelected() == 3) {
            boolean aux = gameSet.split();
        }
        //TODO: finish aux variable
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        applicationStateController.changeState(ApplicationState.MainMenu);
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

    public void setGameType(int n_) {
        gameType = n_;
    }
}
