package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.viewer.Viewer;

import java.io.IOException;

public class DecksMenuViewer implements Viewer {
    int buttonSelected;
    private final LanternaGUI gui;

    public DecksMenuViewer(LanternaGUI gui_){
        buttonSelected = 0;
        gui = gui_;
    }

    public int getButtonSelected() {
        return buttonSelected;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawDecksText();
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawOneButton(buttonSelected == 0);
        gui.drawTwoButton(buttonSelected == 1);
        gui.drawInfButton(buttonSelected == 2);
        gui.drawRetDecks(buttonSelected == 3);
        gui.refresh();
    }


    public void setButtonSelected(int i) {
        buttonSelected = i;
    }
}
