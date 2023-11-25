package com.projLDTS.blackjack.gui;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class UserInput {
    private final LanternaGUI gui;
    public UserInput(LanternaGUI gui_) {gui = gui_; }

    public int MainMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            gui.getScreen().close();
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        else if (key.getKeyType() == KeyType.ArrowUp) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.ArrowDown) {
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 0;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            return 3;
        }
        return -1;
    }

    public int StartMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            gui.getScreen().close();
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        if (key.getKeyType() == KeyType.ArrowRight) {
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 2;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 0;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            return 3;
        }
        return -1;
    }

    public int ExitAndHowToPlayMenuInput() throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            gui.getScreen().close();
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        if (key.getKeyType() == KeyType.ArrowRight) {
            return 1;
        }
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            return 0;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            return 2;
        }
        return -1;
    }
}
