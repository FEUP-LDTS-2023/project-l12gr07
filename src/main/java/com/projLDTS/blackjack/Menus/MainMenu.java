package com.projLDTS.blackjack.Menus;
import com.projLDTS.blackjack.Elements.Button;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;

public class MainMenu {
    private final TerminalScreen screen;
    private final int width = 130;
    private final int height = 40;
    Button start;
    Button howToPlay;
    Button exit;
    int buttonSelected;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    TextColor selectedColor = TextColor.Factory.fromString("#FF0000");

    public MainMenu() throws IOException {
        buttonSelected = 0;

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();

        start = new Button(new TerminalPosition(63, 22), "START", new TerminalSize(30, 3), new TerminalPosition(50, 21), selectedColor);
        howToPlay = new Button(new TerminalPosition(60, 26), "HOW TO PLAY", new TerminalSize(30, 3), new TerminalPosition(50, 25), buttonColor);
        exit = new Button(new TerminalPosition(63, 30), "EXIT", new TerminalSize(30, 3), new TerminalPosition(50, 29), buttonColor);
    }

    private void drawMain() throws IOException {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        //blackjack
        textGraphics.putString(new TerminalPosition(24, 5), "BB          BB                       BB        BB                       BB         \n");
        textGraphics.putString(new TerminalPosition(24, 6), "BB          BB                       BB        \"\"                       BB         \n");
        textGraphics.putString(new TerminalPosition(24, 7), "BB          BB                       BB                                 BB         \n");
        textGraphics.putString(new TerminalPosition(24, 8), "BB,dPPYba,  BB ,adPPYYba,  ,adPPYba, BB   ,dB  BB ,adPPYYba,  ,adPPYba, BB   ,dB   \n");
        textGraphics.putString(new TerminalPosition(24, 9), "BBP'    \"Ba BB \"\"     `YB aB\"     \"\" BB ,aB'   BB \"\"     `YB aB\"     \"\" BB ,aB\"    \n");
        textGraphics.putString(new TerminalPosition(24, 10), "BB       dB BB ,adPPPPPBB Bb         BBBB[     BB ,adPPPPPBB Bb         BBBB[      \n");
        textGraphics.putString(new TerminalPosition(24, 11), "BBb,   ,aB\" BB BB,    ,BB \"Ba,   ,aa BB`\"Yba,  BB BB,    ,BB \"Ba,   ,aa BB`\"Yba,   \n");
        textGraphics.putString(new TerminalPosition(24, 12), "BY\"YbbdB\"'  BB `\"BbbdP\"YB  `\"YbbdB\"' BB   `YBa BB `\"BbbdP\"YB  `\"YbbdB\"' BB   `YBa  \n");
        textGraphics.putString(new TerminalPosition(24, 13), "                                              ,BB                                  \n");
        textGraphics.putString(new TerminalPosition(24, 14), "                                            BBBP\"\n");

        // buttons
        exit.drawButton(textGraphics);
        start.drawButton(textGraphics);
        howToPlay.drawButton(textGraphics);
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
            exit.setColor(buttonColor);
            start.setColor(selectedColor);
            howToPlay.setColor(buttonColor);
            exit.drawButton(textGraphics);
            start.drawButton(textGraphics);
            howToPlay.drawButton(textGraphics);
        }
        if (buttonSelected == 1) {
            start.setColor(buttonColor);
            howToPlay.setColor(selectedColor);
            exit.setColor(buttonColor);
            exit.drawButton(textGraphics);
            start.drawButton(textGraphics);
            howToPlay.drawButton(textGraphics);
        }
        if (buttonSelected == 2) {
            start.setColor(buttonColor);
            howToPlay.setColor(buttonColor);
            exit.setColor(selectedColor);
            exit.drawButton(textGraphics);
            start.drawButton(textGraphics);
            howToPlay.drawButton(textGraphics);
        }
    }

    private boolean processKey(KeyStroke key) throws IOException {
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
                    screen.close();
                    new StartMenu(screen).run();
                    break;
                case 1:
                    //call how to play
                    break;
                default:
                    screen.close();
                    new ExitMenu(screen).run();
            }
            return false;
        }
        return false;
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