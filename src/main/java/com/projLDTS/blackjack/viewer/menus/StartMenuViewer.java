package com.projLDTS.blackjack.viewer.menus;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.IOException;

public class StartMenuViewer implements StateViewer {
    int buttonSelected;
    private final LanternaGUI gui;
    private final UserInput userInput;


    public StartMenuViewer(LanternaGUI gui_){
        buttonSelected = 0;
        gui = gui_;
        userInput = new UserInput(gui);
    }
    public StartMenuViewer(UserInput userInput, LanternaGUI gui_) {
        this.userInput = userInput;
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
        gui.drawGetUsername(UserInput.getUsername());
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawLast10GamesButton(buttonSelected == 2);
        gui.drawbReturnButton(buttonSelected == 0);
        if(UserInput.getUsername().toString() != ""){
            gui.drawPlayButton(buttonSelected == 1);
        }
        gui.refresh();
    }

    @Override
    public int userInput() throws IOException {
        int inputResult = userInput.StartMenuInput(buttonSelected, UserInput.getUsername());
        return inputResult;
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }

}
