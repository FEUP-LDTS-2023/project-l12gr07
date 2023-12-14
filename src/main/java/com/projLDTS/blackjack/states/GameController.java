package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.UserInput;
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
        gameSet = new GameSet(gameType);
        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
        while (true) {
            applicationStateController.redraw();
            drawCards();
            canSplit();
            gameViewer.setSplit(split);
            int aux = userInput();
            if (aux == 3 && !split) continue;
            if (aux == 5) {
                nextState();
                return;
            }
            else if (aux == 4) {
                int a = play();
                if (a == 1 || a == 0) return;
            }
            else {
                setButtonSelected(aux);
            }
        }
    }
    private void drawCards() throws IOException {
        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
        gameViewer.drawCards(gameSet.getPlayer().getHand(), 20);
        gameViewer.drawCards(gameSet.getDealer().getHand(), 10);
        if (split) gameViewer.drawCards(gameSet.getPlayer().getSplitHand(), 20);
    }

    private void drawCards() throws IOException {
        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
        gameViewer.drawCards(gameSet.getPlayer().getHand(), 20);
        gameViewer.drawCards(gameSet.getDealer().getHand(), 10);
        if (split) gameViewer.drawCards(gameSet.getPlayer().getSplitHand(), 20);
    }

    private void canSplit() {
        split = gameSet.canSplit();
    }

    private int play() throws IOException, URISyntaxException, FontFormatException {
        boolean aux = false;
        int staux = -1;
        if (getButtonSelected() == 0) {
            aux = gameSet.hit();
        }
        else if (getButtonSelected() == 1) {
            staux = gameSet.stand();
            if (staux == 0) aux = false;
            else if (staux == 1) aux = true;
        }
        else if (getButtonSelected() == 2) {
            aux = gameSet.doubledown();
        }
        else if (getButtonSelected() == 3 && split) {
            split = false;
            aux = gameSet.split();
        }
        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();

        gameViewer.setAfterPlay(true);
        if (UserInput.getCredit() == 0) {
            UserInput.setCredit(1000);
            gameViewer.playerNoCredit();
            int input = gameViewer.userInput();
            if (input == 0) {
                gameSet.nextGame();
                gameViewer.setAfterPlay(false);
                return 1;
            }
            else if (input == 1) {
                nextState();
                return 0;
            }
        }
        if (staux == 2) {
            // Draw
            while (true) {
                gameViewer.playDraw();
                int input = gameViewer.userInput();
                if (input == 0) {
                    gameSet.nextGame();
                    gameViewer.setAfterPlay(false);
                    return 1;
                }
                else if (input == 1) {
                    nextState();
                    return 0;
                }
            }
        }
        else if (aux && getButtonSelected() == 0) {
            // Keep playing
            gameViewer.setAfterPlay(false);
            return 2;
        }
        else if (!aux) {
            // Player Lost
            while (true) {
                gameViewer.playerLost();
                int input = gameViewer.userInput();
                if (input == 0) {
                    gameSet.nextGame();
                    gameViewer.setAfterPlay(false);
                    return 1;
                }
                else if (input == 1) {
                    nextState();
                    return 0;
                }
            }
        }
        else {
            // Player Won
            while (true) {
                gameViewer.playerWon();
                int input = gameViewer.userInput();
                if (input == 0) {
                    gameSet.nextGame();
                    gameViewer.setAfterPlay(false);
                    return 1;
                }
                else if (input == 1) {
                    nextState();
                    return 0;
                }
            }
        }
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
