package com.projLDTS.blackjack.Menus;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;
import com.projLDTS.blackjack.Board;

import java.awt.*;
import java.io.IOException;

public class Start {
    private final TerminalScreen screen;
    private Board board;
    private final int width = 100;
    private final int height = 35;

    public Start() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    private void drawMain() throws IOException {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.enableModifiers(SGR.BOLD);
        Font myFont = new Font ("Courier New", Font.BOLD, 17);
        //TODO
        // textGraphics.;  --> implement myFont in the terminal
        textGraphics.putString(new TerminalPosition(30, 10), "BLACKJACK");
        screen.refresh();
    }

    private void drawGame() throws IOException {
        board = new Board(60, 60);
        board.draw(screen.newTextGraphics());
        screen.clear();
        board = new Board(width, height);
        board.draw(screen.newTextGraphics());
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