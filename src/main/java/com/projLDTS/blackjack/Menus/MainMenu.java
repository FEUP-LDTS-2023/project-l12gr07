package com.projLDTS.blackjack.Menus;
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
    private final int width = 100;
    private final int height = 35;

    public MainMenu() throws IOException {
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

        //start button
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00CA4C"));
        textGraphics.fillRectangle(new TerminalPosition(35, 18),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(48, 19), "START");

        //how to play button
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00CA4C"));
        textGraphics.fillRectangle(new TerminalPosition(35, 22),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(45, 23), "HOW TO PLAY");

        //exit button
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00CA4C"));
        textGraphics.fillRectangle(new TerminalPosition(35, 26),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(48, 27), "EXIT");

        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        //board.processKey(key);
    }

    public void run() throws IOException {
        while (true) {
            drawMain();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
            processKey(key);
        }
    }
}