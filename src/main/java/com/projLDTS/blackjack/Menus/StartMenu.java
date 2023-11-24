package com.projLDTS.blackjack.Menus;
import Elements.Button;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.projLDTS.blackjack.Board;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.regex.Pattern;


public class StartMenu {
    private final TerminalScreen screen;
    private final int width = 130;
    private final int height = 40;
    private static final int MAX_USERNAME_LENGTH = 20;
    Button bReturn;
    Button play;
    Button last10Games;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    TextColor selectedColor = TextColor.Factory.fromString("#FF0000");
    int buttonSelected;
    //changed: Added variable to store user input
    private StringBuilder username = new StringBuilder();

    public StartMenu(TerminalScreen screen_) throws IOException {
        buttonSelected = 0;
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        last10Games = new Button(new TerminalPosition(115, 36), "LAST GAMES", new TerminalSize(16, 3), new TerminalPosition(112, 35), buttonColor);
        bReturn = new Button(new TerminalPosition(32, 25), "RETURN", new TerminalSize(14, 3), new TerminalPosition(28, 24), selectedColor);
        play = new Button(new TerminalPosition(80, 25), "PLAY", new TerminalSize(12, 3), new TerminalPosition(76, 24), buttonColor);
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

    private boolean processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowRight) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            return true;
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 0;
            return true;
        } else if (key.getKeyType() == KeyType.Enter) {
            switch (buttonSelected) {
                case 0: //return to Main Menu
                    screen.close();
                    new MainMenu().run();
                    break;
                case 1: //Last 10 games
                    screen.close();
                    //new MainMenu().run();
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


    public void drawStart(TerminalScreen screen) throws IOException {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        //box for input
        textGraphics.putString(new TerminalPosition(30, 12), "Insert your username: (Max 20 characters)\n");
        textGraphics.putString(new TerminalPosition(49, 17), username.toString() + "\n");
        int boxWidth = 22;
        int boxHeight = 3;
        int x = 48;
        int y = 16;
        drawBox(textGraphics, x, y, boxWidth, boxHeight, TextColor.Factory.fromString("#028A02"));
        //,TextColor.Factory.fromString("#028A02")
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

            if (processKey(key)) {
                changeSelectedButton(textGraphics);
                screen.refresh();
            } else {
                // Handle input for the TextBox
                handleTextBoxInput(key);

                //changed: Handle input for the Play button
                if (buttonSelected == 2) {
                    handlePlayButtonInput(key);
                }

                screen.clear();
                drawStart(screen);
                screen.refresh();
            }
        }
    }

    private void handlePlayButtonInput(KeyStroke key) throws IOException {
        //changed: If Play button is selected, handle input for username and print it when Enter is pressed
        if (key.getKeyType() == KeyType.Enter) {
            // Print the entered username for testing purposes, replace this with your actual game logic
            System.out.println("Entered username for playing: " + username.toString());

            // Clear the username for the next input
            username.setLength(0);
        }
    }

    private void handleTextBoxInput(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character) {
            char inputChar = key.getCharacter();
            if (inputChar != '\n' && username.length() < MAX_USERNAME_LENGTH) {
                //changed: Append characters to the username StringBuilder if within limit
                username.append(inputChar);
            }
        } else if (key.getKeyType() == KeyType.Backspace) {
            //changed: Handle backspace to remove characters from the username
            if (username.length() > 0) {
                username.deleteCharAt(username.length() - 1);
            }
        }
    }

    //changed: Added method to draw a box
    //changed: Updated drawBox method to position the box below the "Insert your username" phrase
    private void drawBox(TextGraphics textGraphics, int x, int y, int width, int height, TextColor borderColor) {
        /*for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                textGraphics.setForegroundColor(boxColor);
                textGraphics.setCharacter(x + i, y + j, ' ');
            }
        }*/

        for (int i = 0; i < width; i++) {
            textGraphics.setForegroundColor(borderColor);
            textGraphics.setCharacter(x + i, y, '-');
            textGraphics.setCharacter(x + i, y + height - 1, '-');
        }

        for (int i = 1; i < height - 1; i++) {
            textGraphics.setForegroundColor(borderColor);
            textGraphics.setCharacter(x, y + i, '|');
            textGraphics.setCharacter(x + width - 1, y + i, '|');
        }
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
    }
}

