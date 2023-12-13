package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.IOException;

public class ExitMenuViewer implements StateViewer {
    int buttonSelected;
    private final LanternaGUI gui;

    public ExitMenuViewer(LanternaGUI gui_){
        buttonSelected = 0;
        gui = gui_;
    }

    public int getButtonSelected() {
        return buttonSelected;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawExitQ();
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawYesButton(buttonSelected == 0);
        gui.drawNoButton(buttonSelected == 1);
        gui.refresh();
    }


    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    @Override
    public int userInput() throws IOException {
        return new UserInput(gui).ExitAndHowToPlayMenuInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }
}
