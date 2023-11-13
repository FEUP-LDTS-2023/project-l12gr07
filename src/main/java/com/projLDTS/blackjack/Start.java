package com.projLDTS.blackjack;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;

public class Start {
    private final TerminalScreen screen;
    //private final Arena arena;

    public Start() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(60, 30)).createTerminal();
        screen = new TerminalScreen(terminal);

        TerminalPosition position = new TerminalPosition(10, 10);
//        screen.setCursorPosition(position);
        screen.startScreen();
        screen.doResizeIfNecessary();

        //arena = new Arena(width, heigt);
    }

    private void draw() throws IOException {
        screen.clear();
        //arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        //arena.processKey(key);
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
            processKey(key);
        }
    }
}