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

public class HowToPlay {
    private final TerminalScreen screen;
    private final int width = 100;
    private final int height = 35;
    Button ret1;
    Button ret2;
    Button next;
    Button previous;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    TextColor selectedColor = TextColor.Factory.fromString("#FF0000");
    int buttonSelected;
    int currentPage;
    public HowToPlay(TerminalScreen screen_) throws IOException {
        buttonSelected = 0;
        currentPage = 1;
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();

        ret1 = new Button(new TerminalPosition(18, 30), " RETURN", new TerminalSize(15, 3), new TerminalPosition(15, 29), selectedColor);
        next = new Button(new TerminalPosition(76, 30), "-->", new TerminalSize(15, 3), new TerminalPosition(70, 29), buttonColor);
        previous = new Button(new TerminalPosition(18, 30), "<--", new TerminalSize(15, 3), new TerminalPosition(15, 29), buttonColor);
        ret2 = new Button(new TerminalPosition(76, 30), " RETURN", new TerminalSize(15, 3), new TerminalPosition(70, 29), buttonColor);
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
    public void drawHowToPlay() throws IOException {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        drawPageContent(textGraphics);
        drawPageButtons(textGraphics);
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
    private void drawTitle(TextGraphics textGraphics) {
        textGraphics.putString(new TerminalPosition(9, 5), "BB        BB                                   BBBBBBBBBBBB              BBBBBBBBba  BB                         \n");
        textGraphics.putString(new TerminalPosition(9, 6), "BB        BB                                        BB                   BB      \"Bb BB                         \n");
        textGraphics.putString(new TerminalPosition(9, 7), "BB        BB                                        BB                   BB      ,BP BB                         \n");
        textGraphics.putString(new TerminalPosition(9, 8), "BBaaaaaaaaBB  ,adPPYba,  Bb      db      dB         BB  ,adPPYba,        BBaaaaaaBP' BB ,adPPYYba, Bb       dB  \n");
        textGraphics.putString(new TerminalPosition(9, 9), "BB\"\"\"\"\"\"\"\"BB aB\"     \"Ba `Bb    dBBb    dB'         BB aB\"     \"Ba       BB\"\"\"\"\"\"'   BB \"\"     `YB `Bb     dB'  \n");
        textGraphics.putString(new TerminalPosition(9, 10), "BB        BB Bb       dB  `Bb  dB'`Bb  dB'          BB Bb       dB       BB          BB ,adPPPPPBB  `Bb   dB'   \n");
        textGraphics.putString(new TerminalPosition(9, 11), "BB        BB \"Ba,   ,aB\"   `BbdB'  `BbdB'           BB \"Ba,   ,aB\"       BB          BB BB,    ,BB   `Bb,dB'    \n");
        textGraphics.putString(new TerminalPosition(9, 12), "BB        BB  `\"YbbdP\"'      YP      YP             BB  `\"YbbdP\"'        BB          BB `\"BbbdP\"YB     YBB'     \n");
        textGraphics.putString(new TerminalPosition(9, 13), "                                                                                                       dB'      \n");
        textGraphics.putString(new TerminalPosition(9, 14), "                                                                                                      dB'       ");
    }
    private void drawPageButtons(TextGraphics textGraphics) {
        switch (currentPage) {
            case 1:
                ret1.setColor(selectedColor);
                next.setColor(selectedColor);
                ret1.drawButton(textGraphics);
                next.drawButton(textGraphics);
                break;
            case 2, 3:
                previous.setColor(selectedColor);
                next.setColor(selectedColor);
                previous.drawButton(textGraphics);
                next.drawButton(textGraphics);
                break;
            case 4:
                previous.setColor(selectedColor);
                ret2.setColor(selectedColor);
                previous.drawButton(textGraphics);
                ret2.drawButton(textGraphics);
                break;
            default:
                break;
        }
    }
    private void drawPageContent(TextGraphics textGraphics) {
        switch (currentPage) {
            case 1:
                drawTitle(textGraphics);
                // Draw content for page 1
                textGraphics.putString(new TerminalPosition(9, 18), "1. **Objective:**\n");
                textGraphics.putString(new TerminalPosition(9, 19), "   The goal of Blackjack is to beat the dealer by having a hand value");
                textGraphics.putString(new TerminalPosition(9, 21), "2. **Card Values:**\n");
                textGraphics.putString(new TerminalPosition(9, 22), "   - Number cards (2-10) are worth their face value.\n");
                textGraphics.putString(new TerminalPosition(9, 23), "   - Face cards (Jack, Queen, King) are each worth 10.");
                textGraphics.putString(new TerminalPosition(9, 24), "   - Aces can be worth 1 or 11, depending on which value benefits the hand more.\n");
                textGraphics.putString(new TerminalPosition(50, 27), currentPage + "/4");
                break;
            case 2:
                drawTitle(textGraphics);
                // Draw content for page 2
                textGraphics.putString(new TerminalPosition(9, 18), "3. **Game Flow:**\n");
                textGraphics.putString(new TerminalPosition(9, 19), "   - Each player, including the dealer, is dealt two cards.\n");
                textGraphics.putString(new TerminalPosition(9, 20), "   - Players can 'Hit' to receive additional cards or 'Stand' to keep their current hand.\n");
                textGraphics.putString(new TerminalPosition(9, 21), "   - If the total value of a player's cards exceeds 21, they bust and lose the round.\n");
                textGraphics.putString(new TerminalPosition(9, 24), "4. **Winning:**\n");
                textGraphics.putString(new TerminalPosition(9, 25), "   - If a player's total is closer to 21 than the dealer's, they win.\n");
                textGraphics.putString(new TerminalPosition(9, 26), "   - If the dealer busts and the player doesn't, the player wins.\n");
                textGraphics.putString(new TerminalPosition(9, 27), "   - If both the player and dealer have the same total, it's a push (no one wins or loses).\n");
                textGraphics.putString(new TerminalPosition(50, 30), currentPage + "/4");
                break;
            case 3:
                drawTitle(textGraphics);
                // Draw content for page 3
                textGraphics.putString(new TerminalPosition(9, 18), "5. **Special Moves:**\n");
                textGraphics.putString(new TerminalPosition(9, 19), "   - **Double Down:** Double your original bet and receive only one additional card.\n");
                textGraphics.putString(new TerminalPosition(9, 20), "   - **Split:** If dealt two cards of the same rank, split them into two separate hands, each with its bet.\n");
                textGraphics.putString(new TerminalPosition(9, 23), "6. **Blackjack:**\n");
                textGraphics.putString(new TerminalPosition(9, 24), "   - A \"Blackjack\" is an Ace and a 10-value card. It usually pays 3:2.\n");
                textGraphics.putString(new TerminalPosition(50, 30), currentPage + "/4");
                break;
            case 4:
                drawTitle(textGraphics);
                // Draw content for page 2
                textGraphics.putString(new TerminalPosition(9, 16), "7. **Tips:**\n");
                textGraphics.putString(new TerminalPosition(9, 17), "   - Pay attention to the dealer's upcard and adjust your strategy accordingly.\n");
                textGraphics.putString(new TerminalPosition(9, 18), "   - Practice basic strategy to maximize your chances of winning.\n");
                textGraphics.putString(new TerminalPosition(9, 21), "8. **Commands:**\n");
                textGraphics.putString(new TerminalPosition(9, 22), "   - Type 'h' to Hit.\n");
                textGraphics.putString(new TerminalPosition(9, 23), "   - Type 's' to Stand.\n");
                textGraphics.putString(new TerminalPosition(9, 24), "   - Type 'd' to Double Down.\n");
                textGraphics.putString(new TerminalPosition(9, 25), "   - Type 'p' to Split (if eligible).\n");
                textGraphics.putString(new TerminalPosition(9, 28), "9. **Have Fun and Good Luck!**\n");
                textGraphics.putString(new TerminalPosition(50, 30), currentPage + "/4");
                break;
            default:
                break;
        }
    }
    private void changeSelectedButton(TextGraphics textGraphics) {
        switch (currentPage) {
            case 1:
                if (buttonSelected == 0) {
                    ret1.setColor(selectedColor);
                    next.setColor(buttonColor);
                    ret1.drawButton(textGraphics);
                    next.drawButton(textGraphics);
                }
                if (buttonSelected == 1) {
                    ret1.setColor(buttonColor);
                    next.setColor(selectedColor);
                    ret1.drawButton(textGraphics);
                    next.drawButton(textGraphics);
                }
            case 2, 3:
                if (buttonSelected == 0) {
                    previous.setColor(selectedColor);
                    next.setColor(buttonColor);
                    previous.drawButton(textGraphics);
                    next.drawButton(textGraphics);
                }
                if (buttonSelected == 1) {
                    previous.setColor(buttonColor);
                    next.setColor(selectedColor);
                    previous.drawButton(textGraphics);
                    next.drawButton(textGraphics);
                }
            case 4:
                if (buttonSelected == 0) {
                    previous.setColor(selectedColor);
                    ret2.setColor(buttonColor);
                    previous.drawButton(textGraphics);
                    ret2.drawButton(textGraphics);
                }
                if (buttonSelected == 1) {
                    previous.setColor(buttonColor);
                    ret2.setColor(selectedColor);
                    previous.drawButton(textGraphics);
                    ret2.drawButton(textGraphics);
                }
        }

    }
    private boolean processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowLeft) {
            if (currentPage > 1) {
                currentPage--;
                buttonSelected = 0;
                drawHowToPlay();
            } else if (currentPage == 2) {
                handleNextPage();
            }
            return true;
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            if (currentPage < 4) {
                currentPage++;
                buttonSelected = 0;
                drawHowToPlay();
            } else if (currentPage == 2) {
                handlePreviousPage();
            }
            return true;
        } else if (key.getKeyType() == KeyType.Enter) {
            switch (buttonSelected) {
                case 0:
                    screen.close();
                    new MainMenu().run();
                    break;
                case 1:
                    if (currentPage == 2) {
                        handleNextPage();
                    }
                    break;
                default:
                    screen.close();
                    break;
            }
            return false;
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
            case 2:
                drawHowToPlay();
                break;
            case 3:
                drawHowToPlay();
                break;
            default:
                break;
        }
    }
}
