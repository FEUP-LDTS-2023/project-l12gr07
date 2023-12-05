package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.IOException;

public class Last10GamesMenuViewer implements StateViewer {
    int buttonSelected;
    private final LanternaGUI gui;

    public Last10GamesMenuViewer(LanternaGUI gui_){
        buttonSelected = 0;
        gui = gui_;
    }

    public int getButtonSelected() {
        return buttonSelected;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawLast10GamesText();
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawRetDecks(buttonSelected == 0);
        gui.refresh();
    }


    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    @Override
    public int getPage() {return 0;}

    @Override
    public void setPage(int i) {}

    @Override
    public int userInput() throws IOException {
        return new UserInput(gui).Last10GamesMenuInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }
}
