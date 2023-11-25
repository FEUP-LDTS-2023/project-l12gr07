package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.viewer.Viewer;

import java.io.IOException;

public class StartMenuViewer implements Viewer {
    int buttonSelected;
    private final LanternaGUI gui;
    private StringBuilder username = new StringBuilder();

    public StartMenuViewer(LanternaGUI gui_){
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
        //gui.drawBlackjack();
        gui.drawGetUsername(username);
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawLast10GamesButton(false);
        gui.drawbReturnButton(false);
        gui.drawPlayButton(false);
        if (buttonSelected == 0) gui.drawLast10GamesButton(true);
        else if (buttonSelected == 1) gui.drawbReturnButton(true);
        else if (buttonSelected == 2) gui.drawPlayButton(true);
        gui.refresh();
    }
}