package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.IOException;

public class DecksMenuViewer implements StateViewer {
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
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawDecksText();
        gui.drawOneButton(buttonSelected == 0);
        gui.drawTwoButton(buttonSelected == 1);
        gui.drawInfButton(buttonSelected == 2);
        gui.drawRetDecks(buttonSelected == 3);
    }


    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    @Override
    public int userInput() throws IOException {
        return new UserInput(gui).DecksMenuInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }
}
