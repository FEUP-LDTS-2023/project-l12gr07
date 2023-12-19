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
    private boolean dd = true;

    public GameController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException, InterruptedException {
        gameSet = new GameSet(gameType);
        while (true) {
            applicationStateController.redraw();
            drawInitialCards();
            System.out.println("HERE");
            int aux = userInput();
            if (aux == 5) {
                nextState();
                return;
            }
            else if (aux == 4) {
                dd = false;
                int a = play();
                // setButtonSelected(0);
                if (a == 1 || a == 0) return;
            }
            else {
                if (aux != 2 || dd) {
                    setButtonSelected(aux);
                }
            }
        }
    }

    private void drawInitialCards() throws IOException {
        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
        gameViewer.drawFirstCards(gameSet.getPlayer().getHand(), 20, false);
        gameViewer.drawFirstCards(gameSet.getDealer().getHand(), 10, true);
    }

    private void drawCards() throws IOException, InterruptedException {
        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
        gameViewer.drawCards(gameSet.getPlayer().getHand(), 20, false);
        gameViewer.drawCards(gameSet.getDealer().getHand(), 10, true);
    }


    private int play() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        boolean aux = false;
        int staux = 3;
        if (getButtonSelected() == 0) {
            aux = gameSet.hit();
        }
        else if (getButtonSelected() == 1) {
            while (staux == 3) {
                staux = gameSet.stand();
                if (staux == 0) aux = false;
                else if (staux == 1) aux = true;
                drawCards();
            }
            System.out.println(staux);
        }
        else if (getButtonSelected() == 2) {
            aux = gameSet.doubledown();
        }

        GameViewer gameViewer = (GameViewer) applicationStateController.getStateViewer();
        applicationStateController.redraw();
        drawInitialCards();
        gameViewer.setAfterPlay(true);

        if (staux == 2) {
            // Draw
            while (true) {
                gameViewer.playDraw();
                int input = gameViewer.userInput();
                gameViewer.setAfterPlay(false);
                if (input == 0) {
                    dd = true;
                    gameSet.nextGame();
                    return 1;
                }
                else if (input == 1) {
                    nextState();
                    return 0;
                }
            }
        }
        else if (aux && (getButtonSelected() == 0 || getButtonSelected() == 2)) {
            // Keep playing
            gameViewer.setAfterPlay(false);
            return 2;
        }
        else if (!aux) {
            // Player Lost
            if (UserInput.getCredit() == 0) {
                UserInput.setCredit(1000);
                gameViewer.playerNoCredit();
                int input = gameViewer.userInput();
                gameViewer.setAfterPlay(false);
                if (input == 0) {
                    dd = true;
                    gameSet.nextGame();
                    return 1;
                }
                else if (input == 1) {
                    nextState();
                    return 0;
                }
            }
            while (true) {
                gameViewer.playerLost();
                int input = gameViewer.userInput();
                gameViewer.setAfterPlay(false);
                if (input == 0) {
                    dd = true;
                    gameSet.nextGame();
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
                gameViewer.setAfterPlay(false);
                if (input == 0) {
                    dd = true;
                    gameSet.nextGame();
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

    public void setDd(boolean dd) {
        this.dd = dd;
    }
}
