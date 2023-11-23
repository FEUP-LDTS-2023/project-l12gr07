package com.projLDTS.blackjack.Menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.projLDTS.blackjack.Board;

import java.io.IOException;
import java.util.regex.Pattern;
public class StartMenu {
    private Board board;
    private final int width = 100;
    private final int height = 35;
    public StartMenu(TerminalScreen screen) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }
    private void drawStart(TerminalScreen screen){
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        //Insert your name:
        textGraphics.putString(new TerminalPosition(30, 12), "Insert your username:\n");

        //Box to write username
        TextBox textBox = new TextBox();
        new TextBox(new TerminalSize(30,5));
        new TextBox(new TerminalSize(10, 1)).withBorder(Borders.singleLine("Heading"));
        new TextBox(new TerminalSize(10, 1), "yourUsername");


    }
    private void drawGame(TerminalScreen screen) throws IOException {
        board = new Board(60, 60);
        board.draw(screen.newTextGraphics());
        screen.clear();
        board = new Board(width, height);
        board.draw(screen.newTextGraphics());
        screen.refresh();
    }
}
