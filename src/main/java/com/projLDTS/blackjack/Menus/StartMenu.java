package com.projLDTS.blackjack.Menus;

import Elements.Button;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.projLDTS.blackjack.Board;

import java.io.IOException;
import java.util.regex.Pattern;
public class StartMenu {
    private final TerminalScreen screen;
    private Board board;
    private final int width = 100;
    private final int height = 35;
    Button bReturn;
    Button play;
    Button last10Games;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    TextColor selectedColor = TextColor.Factory.fromString("#FF0000");
    int buttonSelected;
    public StartMenu(TerminalScreen screen_) throws IOException {
        buttonSelected = 0;
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        last10Games = new Button(new TerminalPosition(85, 31), "LAST GAMES", new TerminalSize(15, 3), new TerminalPosition(82, 30), buttonColor);
        bReturn = new Button(new TerminalPosition(25, 25), "RETURN", new TerminalSize(17, 3), new TerminalPosition(19, 24), buttonColor);
        play = new Button(new TerminalPosition(70, 25), "PLAY", new TerminalSize(15, 3), new TerminalPosition(64, 24), selectedColor);
    }
    public void run() throws IOException {
        while (true) {
            drawStart(screen);
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }
    public void drawStart(TerminalScreen screen) throws IOException {
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

        //buttons
        bReturn.drawButton(textGraphics);
        last10Games.drawButton(textGraphics);
        play.drawButton(textGraphics);
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
    /*private void drawGame(TerminalScreen screen) throws IOException {
        board = new Board(60, 60);
        board.draw(screen.newTextGraphics());
        screen.clear();
        board = new Board(width, height);
        board.draw(screen.newTextGraphics());
        screen.refresh();
    }*/
    private void changeSelectedButton(TextGraphics textGraphics) {
        if (buttonSelected == 0) {
            bReturn.setColor(selectedColor);
            last10Games.setColor(buttonColor);
            play.setColor(buttonColor);
            bReturn.drawButton(textGraphics);
            last10Games.drawButton(textGraphics);
            play.drawButton(textGraphics);
        }
        if (buttonSelected == 1) {
            last10Games.setColor(selectedColor);
            bReturn.setColor(buttonColor);
            play.setColor(buttonColor);
            last10Games.drawButton(textGraphics);
            bReturn.drawButton(textGraphics);
            play.drawButton(textGraphics);
        }
        if (buttonSelected == 2) {
            play.setColor(selectedColor);
            last10Games.setColor(buttonColor);
            bReturn.setColor(buttonColor);
            play.drawButton(textGraphics);
            last10Games.drawButton(textGraphics);
            bReturn.drawButton(textGraphics);
        }
    }

    private boolean processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowRight) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            return true;
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 0;
            return true;
        }else if (key.getKeyType() == KeyType.Enter) {
            switch (buttonSelected) {
                case 0: //return to Main Menu
                    screen.close();
                    new MainMenu().run();
                    break;
                case 1: //Last 10 games
                    screen.close();
                    // call last 10 games menu
                    break;
                case 2: // Play
                    screen.close();
                    // call game
                    break;
                default:

            }
            return false;
        }
        return false;
    }

}
