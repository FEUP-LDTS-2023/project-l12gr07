package com.projLDTS.blackjack.viewer.game;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
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
        gui.refresh();
    }

    public int getButtonSelected() {
        return buttonSelected;
    }
    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void setPage(int i) {}

    @Override
    public int userInput() throws IOException {
        if (afterPlay) return new UserInput(gui).pLostInput();
        return new UserInput(gui).GameInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }

    public void drawElements() throws IOException {
        gui.drawHitButton(false);
        gui.drawStandButton(false);
        gui.drawDoubleDownButton(false);
        gui.drawSplitButton(false, split);
        if (buttonSelected == 0) gui.drawHitButton(true);
        else if (buttonSelected == 1) gui.drawStandButton(true);
        else if (buttonSelected == 2) gui.drawDoubleDownButton(true);
        else if (buttonSelected == 3) gui.drawSplitButton(true, split);
        gui.refresh();
    }

    public void setSplit(boolean i) {
        split = i;
    }

    public void playerLost() throws IOException {
        gui.drawPlayerLost();
        gui.refresh();
    }

    public void setAfterPlay(boolean i) {
        afterPlay = i;
    }

    public void playerWon() throws IOException {
        gui.drawPlayerWon();
        gui.refresh();
    }
}
