package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.model.game.Decks.GameSet;
import com.projLDTS.blackjack.viewer.game.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController implements StateController {
    private ApplicationStateController applicationStateController;
    private GameSet gameSet;
    private int gameType;
    private boolean split = false;

    public GameController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        // TODO: POR ACABAR
        gameSet = new GameSet(gameType);
        applicationStateController.redraw();
        while (true) {
            canSplit();
            GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
            gameViewer.setSplit(split);
            int aux = userInput();
            if (aux == 3 && !split) continue;
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
            gameViewer.drawCards(gameSet.getPlayer().getHand());
            gameViewer.drawCards(gameSet.getDealer().getHand());
            if (split) gameViewer.drawCards(gameSet.getPlayer().getSplitHand());
        }
    }

    private void canSplit() {
        split = gameSet.canSplit();
    }

    private void play() throws IOException, URISyntaxException, FontFormatException {
        boolean aux = false;
        if (getButtonSelected() == 0) {
            aux = gameSet.hit();
        }
        else if (getButtonSelected() == 1) {
            gameSet.stand();
        }
        else if (getButtonSelected() == 2) {
            aux = gameSet.doubledown();
        }
        else if (getButtonSelected() == 3 && split) {
            split = false;
            aux = gameSet.split();
        }
        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
        if (!aux) {
            // Player Lost
            gameViewer.setAfterPlay(true);
            while (true) {
                gameViewer.playerLost();
                int input = gameViewer.userInput();
                if (input == 0) {
                    gameSet.nextGame();
                    break;
                }
                else if (input == 1) {
                    nextState();
                    break;
                }
            }
        }
        else {
            // Player Won
            gameViewer.setAfterPlay(true);
            while (true) {
                gameViewer.playerWon();
                int input = gameViewer.userInput();
                if (input == 0) {
                    gameSet.nextGame();
                    break;
                }
                else if (input == 1) {
                    nextState();
                    break;
                }
            }
        }
        gameViewer.setAfterPlay(false);
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
