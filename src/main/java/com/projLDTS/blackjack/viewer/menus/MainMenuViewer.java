package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.IOException;

public class MainMenuViewer implements StateViewer {
    int buttonSelected;
    private final LanternaGUI gui;

    public MainMenuViewer(LanternaGUI gui_){
        buttonSelected = 0;
        gui = gui_;
    }

    @Override
    public int getButtonSelected() {
        return buttonSelected;
    }

    @Override
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

    @Override
    public int userInput() throws IOException {
        return new UserInput(gui).MainMenuInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }
}
