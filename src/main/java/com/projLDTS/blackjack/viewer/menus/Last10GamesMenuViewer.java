package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Last10GamesMenuViewer implements StateViewer {
    int buttonSelected;
    private final LanternaGUI gui;
    List last10games;

    public Last10GamesMenuViewer(LanternaGUI gui_, List last10games){
        buttonSelected = 0;
        gui = gui_;
        this.last10games = last10games;
    }

    public int getButtonSelected() {
        return buttonSelected;
    }

    private void drawCSV() throws FileNotFoundException {
        int i = 12;
        while (i - 12 < last10games.size()) {
            gui.drawAPlay((String) last10games.get(i - 12), i);
            i++;
        }
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawLast10GamesText();
        drawElements();
        //drawCSV();
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
    public int userInput() throws IOException {
        return new UserInput(gui).Last10GamesMenuInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }
}
