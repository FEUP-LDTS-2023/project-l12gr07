package com.projLDTS.blackjack.viewer.game;

import com.projLDTS.blackjack.gui.LanternaGUI;

import java.io.IOException;


public class GameViewer {
    private final LanternaGUI gui;

    int buttonSelected;

    public GameViewer(LanternaGUI gui_) {
        gui = gui_;
    }

    public void draw() throws IOException {
        gui.clear();
        gui.drawCredit();
        gui.drawLine();
        gui.drawBet();
        gui.refresh();
    }

    public int getButtonSelected() {
        return buttonSelected;
    }
    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    public void drawElements() throws IOException {
        gui.drawHitButton(false);
        gui.drawStandButton(false);
        gui.drawDoubleDownButton(false);
        gui.drawSplitButton(false);
        if (buttonSelected == 0) gui.drawHitButton(true);
        else if (buttonSelected == 1) gui.drawStandButton(true);
        else if (buttonSelected == 2) gui.drawDoubleDownButton(true);
        else if (buttonSelected == 3) gui.drawSplitButton(true);
        gui.refresh();
    }
}
