package com.projLDTS.blackjack.Menus;

import Elements.Button;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ExitMenu {
    private final TerminalScreen screen;
    private final int width = 100;
    private final int height = 35;
    Button yes;
    Button no;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    TextColor selectedColor = TextColor.Factory.fromString("#FF0000");
    int buttonSelected;
    public ExitMenu(TerminalScreen screen_) throws IOException {
        buttonSelected = 0;
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        yes = new Button(new TerminalPosition(25, 25), "YES", new TerminalSize(15, 3), new TerminalPosition(19, 24), selectedColor);
        no = new Button(new TerminalPosition(70, 25), "NO", new TerminalSize(15, 3), new TerminalPosition(64, 24), buttonColor);
    }
    public void run() throws IOException {
        while (true) {
            drawExit();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }
    public void drawExit() throws IOException {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        //blackjack
        textGraphics.putString(new TerminalPosition(9, 5), "BB          BB                       BB        BB                       BB         \n");
        textGraphics.putString(new TerminalPosition(9, 6), "BB          BB                       BB        \"\"                       BB         \n");
        textGraphics.putString(new TerminalPosition(9, 7), "BB          BB                       BB                                 BB         \n");
        textGraphics.putString(new TerminalPosition(9, 8), "BB,dPPYba,  BB ,adPPYYba,  ,adPPYba, BB   ,dB  BB ,adPPYYba,  ,adPPYba, BB   ,dB   \n");
        textGraphics.putString(new TerminalPosition(9, 9), "BBP'    \"Ba BB \"\"     `YB aB\"     \"\" BB ,aB'   BB \"\"     `YB aB\"     \"\" BB ,aB\"    \n");
        textGraphics.putString(new TerminalPosition(9, 10), "BB       dB BB ,adPPPPPBB Bb         BBBB[     BB ,adPPPPPBB Bb         BBBB[      \n");
        textGraphics.putString(new TerminalPosition(9, 11), "BBb,   ,aB\" BB BB,    ,BB \"Ba,   ,aa BB`\"Yba,  BB BB,    ,BB \"Ba,   ,aa BB`\"Yba,   \n");
        textGraphics.putString(new TerminalPosition(9, 12), "BY\"YbbdB\"'  BB `\"BbbdP\"YB  `\"YbbdB\"' BB   `YBa BB `\"BbbdP\"YB  `\"YbbdB\"' BB   `YBa  \n");
        textGraphics.putString(new TerminalPosition(9, 13), "                                              ,BB                                  \n");
        textGraphics.putString(new TerminalPosition(9, 14), "                                            BBBP\"\n");

        textGraphics.putString(new TerminalPosition(35, 20), "Are you sure you want to");
        textGraphics.putString(new TerminalPosition(40, 21), "leave the game?");
        yes.drawButton(textGraphics);
        no.drawButton(textGraphics);
        screen.refresh();

        while (true) {
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
            boolean arrow = processKey(key);
            if (!arrow) continue;
            changeSelectedButton(textGraphics);
            screen.refresh();
        }
    }

    private void changeSelectedButton(TextGraphics textGraphics) {
        if (buttonSelected == 0) {
            yes.setColor(selectedColor);
            no.setColor(buttonColor);
            yes.drawButton(textGraphics);
            no.drawButton(textGraphics);
        }
        if (buttonSelected == 1) {
            yes.setColor(buttonColor);
            no.setColor(selectedColor);
            yes.drawButton(textGraphics);
            no.drawButton(textGraphics);
        }
    }

    private boolean processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowLeft) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 1;
            return true;
        }
        else if (key.getKeyType() == KeyType.ArrowRight) {
            buttonSelected++;
            if (buttonSelected == 2) buttonSelected = 0;
            return true;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            switch (buttonSelected) {
                case 0:
                    screen.close();
                    break;
                default:
                    new MainMenu().run();
                    break;
            }
            return false;
        }
        return false;
    }
}
