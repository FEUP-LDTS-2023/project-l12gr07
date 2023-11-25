package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.viewer.Viewer;

import java.io.IOException;

public class MainMenuViewer implements Viewer {
    int buttonSelected;
    private final LanternaGUI gui;

    public MainMenuViewer(LanternaGUI gui_){
        buttonSelected = 0;
        gui = gui_;
    }

    public int getButtonSelected() {
        return buttonSelected;
    }

    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawBlackjack();
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawStartButton(false);
        gui.drawHowToPlayButton(false);
        gui.drawExitButton(false);
        if (buttonSelected == 0) gui.drawStartButton(true);
        else if (buttonSelected == 1) gui.drawHowToPlayButton(true);
        else if (buttonSelected == 2) gui.drawExitButton(true);
        gui.refresh();
    }
}
