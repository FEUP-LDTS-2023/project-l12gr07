package com.projLDTS.blackjack.viewer.game;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Cards.Hand;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.IOException;


public class GameViewer implements StateViewer {
    private final LanternaGUI gui;
    int buttonSelected;
    boolean split;
    boolean afterPlay = false;
    public GameViewer(LanternaGUI gui_) {
        gui = gui_;
    }

    public void draw() throws IOException {
        gui.clear();
        gui.drawExit();
        gui.drawCredit();
        gui.drawLine();
        gui.drawBet();
        drawElements();
        gui.drawDealerPlayerText();
        gui.refresh();
    }

    public void refreshCreditBet() {
        gui.drawCredit();
        gui.drawBet();
    }

    public int getButtonSelected() {
        return buttonSelected;
    }
    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    @Override
    public int userInput() throws IOException {
        if (afterPlay) return new UserInput(gui).playInput();
        return new UserInput(gui).GameInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }

    public void drawElements() throws IOException {
        if(!UserInput.getBet().toString().isEmpty() && UserInput.getbetEnded()){
            gui.drawHitButton(buttonSelected == 0);
            gui.drawStandButton(buttonSelected == 1);
            gui.drawDoubleDownButton(buttonSelected == 2);
        }
        gui.refresh();
    }

    public void playerLost() throws IOException {
        gui.drawPlayerLost();
        gui.refresh();
    }

    public void playerNoCredit() throws IOException {
        gui.drawPlayerNoCredit();
        gui.refresh();
    }

    public void setAfterPlay(boolean i) {
        afterPlay = i;
    }

    public void playerWon() throws IOException {
        gui.drawPlayerWon();
        gui.refresh();
    }

    public void drawCards(Hand hand, int row, boolean dealer) throws IOException, InterruptedException {
        if(!UserInput.getBet().toString().isEmpty() && UserInput.getbetEnded()) {
            if (dealer) gui.drawDealerCards(hand, row);
            else gui.drawCards(hand, row);
            gui.drawValues(hand.getValue(), dealer);
            gui.refresh();
        }
    }

    public void drawFirstCards(Hand hand, int row, boolean dealer) throws IOException {
        if(!UserInput.getBet().toString().isEmpty() && UserInput.getbetEnded()) {
            gui.drawCards(hand, row);
            gui.drawValues(hand.getValue(), dealer);
            gui.refresh();
        }
    }

    public void playDraw() throws IOException {
        gui.drawPlayDraw();
        gui.refresh();
    }
}
