package com.projLDTS.blackjack.Menus;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.MouseAction;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;
import java.util.Vector;

public class MainMenu {
    private final TerminalScreen screen;
    private final int width = 100;
    private final int height = 35;
    private final TerminalPosition startButtonPos = new TerminalPosition(35, 18);
    private final TerminalPosition howToPlayButtonPos = new TerminalPosition(35, 22);
    private final TerminalPosition exitButtonPos = new TerminalPosition(35, 26);
    private final TerminalPosition startStringPos = new TerminalPosition(48, 19);
    private final TerminalPosition howToPlayStringPos = new TerminalPosition(45, 23);
    private final TerminalPosition exitStringPos = new TerminalPosition(48, 27);
    private final TerminalSize buttonSize = new TerminalSize(30, 3);
    private final TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    private final TextColor selectedButtonColor = TextColor.Factory.fromString("#FF0000");
    private final Vector<String> buttons = new Vector<>();
    int buttonSelected;

    public MainMenu() throws IOException {
        buttonSelected = 0;
        buttons.add("START");
        buttons.add("HOW TO PLAY");
        buttons.add("EXIT");

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
    }

    private void drawMain() throws IOException {
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

        // buttons
        drawButton(selectedButtonColor, textGraphics, startButtonPos, startStringPos, "START");
        drawButton(buttonColor, textGraphics, howToPlayButtonPos, howToPlayStringPos, "HOW TO PLAY");
        drawButton(buttonColor, textGraphics, exitButtonPos, exitStringPos, "EXIT");
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
            drawButton(buttonColor, textGraphics, exitButtonPos, exitStringPos, "EXIT");
            drawButton(selectedButtonColor, textGraphics, startButtonPos, startStringPos, "START");
            drawButton(buttonColor, textGraphics, howToPlayButtonPos, howToPlayStringPos, "HOW TO PLAY");
        }
        if (buttonSelected == 1) {
            drawButton(buttonColor, textGraphics, startButtonPos, startStringPos, "START");
            drawButton(selectedButtonColor, textGraphics, howToPlayButtonPos, howToPlayStringPos, "HOW TO PLAY");
            drawButton(buttonColor, textGraphics, exitButtonPos, exitStringPos, "EXIT");
        }
        if (buttonSelected == 2) {
            drawButton(buttonColor, textGraphics, startButtonPos, startStringPos, "START");
            drawButton(buttonColor, textGraphics, howToPlayButtonPos, howToPlayStringPos, "HOW TO PLAY");
            drawButton(selectedButtonColor, textGraphics, exitButtonPos, exitStringPos, "EXIT");
        }
    }

    private boolean processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            return true;
        }
        else if (key.getKeyType() == KeyType.ArrowDown) {
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 0;
            return true;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            switch (buttonSelected) {
                case 0:
                    //call start menu
                    break;
                case 1:
                    //call how to play
                    break;
                default:
                    //call exit
            }
            return false;
        }
        return false;
    }

    private void drawButton(TextColor color, TextGraphics textGraphics, TerminalPosition buttonPos,TerminalPosition stringPos, String str) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(color);
        textGraphics.fillRectangle(buttonPos,buttonSize,' ');
        textGraphics.putString(stringPos, str);
    }

    public void run() throws IOException {
        while (true) {
            drawMain();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }
}