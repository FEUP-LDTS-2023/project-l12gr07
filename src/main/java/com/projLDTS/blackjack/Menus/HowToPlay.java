package com.projLDTS.blackjack.Menus;

import com.projLDTS.blackjack.Elements.Button;
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

public class HowToPlay {
    private final TerminalScreen screen;
    private final int width = 130;
    private final int height = 40;
    Button ret1;
    Button ret2;
    Button next;
    Button previous;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    int buttonSelected;
    int currentPage;
    public HowToPlay(TerminalScreen screen_) throws IOException {
        buttonSelected = 0;
        currentPage = 1;
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();

        ret1 = new Button(new TerminalPosition(30, 34), "RETURN", new TerminalSize(50, 3), new TerminalPosition(9, 33), buttonColor);
        next = new Button(new TerminalPosition(94, 34), "-->", new TerminalSize(50, 3), new TerminalPosition(70, 33), buttonColor);
        previous = new Button(new TerminalPosition(30, 34), "<--", new TerminalSize(50, 3), new TerminalPosition(9, 33), buttonColor);
        ret2 = new Button(new TerminalPosition(94, 34), "RETURN", new TerminalSize(50, 3), new TerminalPosition(70, 33), buttonColor);
    }
    public void run() throws IOException {
        while (true) {
            drawHowToPlay();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }


    private boolean processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowLeft) {
            if (currentPage > 1) {
                currentPage--;
                buttonSelected = 0;
                handlePreviousPage();
                drawHowToPlay();
            } else {
                screen.close();
                new MainMenu().run();
            }
            return true;
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            if (currentPage < 4) {
                currentPage++;
                buttonSelected = 0;
                handleNextPage();
                drawHowToPlay();
            } else {
                screen.close();
                new MainMenu().run();
            }
            return true;
        }
        return false;
    }
    private void handleNextPage() throws IOException {
        if (currentPage < 4) {
            currentPage++;
            drawHowToPlay();
        }
    }
    private void handlePreviousPage() throws IOException {
        switch (currentPage) {
            case 2, 3:
                drawHowToPlay();
                break;
            default:
                break;
        }
    }
}
