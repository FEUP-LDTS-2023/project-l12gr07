package com.projLDTS.blackjack.viewer.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.viewer.Viewer;

import java.io.IOException;

public class HowToPlayViewer implements Viewer {
    public void drawHowToPlay(TextGraphics textGraphics) throws IOException {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        drawPageContent(textGraphics);
        drawPageButtons(textGraphics);

        while (true) {
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
            boolean arrow = processKey(key);
            if (!arrow) continue;
            screen.refresh();
        }
    }

    private void drawPageButtons(TextGraphics textGraphics) {
        switch (currentPage) {
            case 1:
                ret1.drawButton(textGraphics);
                next.drawButton(textGraphics);
                break;
            case 2, 3:
                previous.drawButton(textGraphics);
                next.drawButton(textGraphics);
                break;
            case 4:
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
                textGraphics.putString(new TerminalPosition(63, 30), currentPage + "/4");
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
                textGraphics.putString(new TerminalPosition(63, 30), currentPage + "/4");
                break;
            case 3:
                drawTitle(textGraphics);
                // Draw content for page 3
                textGraphics.putString(new TerminalPosition(9, 18), "5. **Special Moves:**\n");
                textGraphics.putString(new TerminalPosition(9, 19), "   - **Double Down:** Double your original bet and receive only one additional card.\n");
                textGraphics.putString(new TerminalPosition(9, 20), "   - **Split:** If dealt two cards of the same rank, split them into two separate hands, each with its bet.\n");
                textGraphics.putString(new TerminalPosition(9, 23), "6. **Blackjack:**\n");
                textGraphics.putString(new TerminalPosition(9, 24), "   - A \"Blackjack\" is an Ace and a 10-value card. It usually pays 3:2.\n");
                textGraphics.putString(new TerminalPosition(63, 30), currentPage + "/4");
                break;
            case 4:
                drawTitle(textGraphics);
                // Draw content for page 2
                textGraphics.putString(new TerminalPosition(9, 17), "7. **Tips:**\n");
                textGraphics.putString(new TerminalPosition(9, 18), "   - Pay attention to the dealer's upcard and adjust your strategy accordingly.\n");
                textGraphics.putString(new TerminalPosition(9, 19), "   - Practice basic strategy to maximize your chances of winning.\n");
                textGraphics.putString(new TerminalPosition(9, 21), "8. **Commands:**\n");
                textGraphics.putString(new TerminalPosition(9, 22), "   - Type 'h' to Hit.\n");
                textGraphics.putString(new TerminalPosition(9, 23), "   - Type 's' to Stand.\n");
                textGraphics.putString(new TerminalPosition(9, 24), "   - Type 'd' to Double Down.\n");
                textGraphics.putString(new TerminalPosition(9, 25), "   - Type 'p' to Split (if eligible).\n");
                textGraphics.putString(new TerminalPosition(9, 27), "9. **Have Fun and Good Luck!**\n");
                textGraphics.putString(new TerminalPosition(63, 30), currentPage + "/4");
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(LanternaGUI gui) throws IOException {

    }

    @Override
    protected void drawElements(LanternaGUI gui) {

    }
}
