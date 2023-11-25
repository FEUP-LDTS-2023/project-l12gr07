package com.projLDTS.blackjack.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LanternaGUI {
    private final Screen screen;
    private TerminalSize size;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException {
        size = new TerminalSize(width, height);
        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        File fontFile = new File("resources/GamePlayed.ttf");
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font loadedFont = font.deriveFont(Font.PLAIN, 50);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);

        Terminal terminal = factory.setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
    }
    public Screen getScreen() {
        return this.screen;
    }
    public void drawBlackjack(TextGraphics textGraphics) throws IOException {
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
        screen.refresh();

    }

    private void drawHowToPlay(TextGraphics textGraphics) {
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

    private void drawExitButton(TextGraphics textGraphics,TextColor color) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(color);
        textGraphics.fillRectangle(new TerminalPosition(50, 29),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(63, 30), "EXIT");
    }

    private void drawStartButton(TextGraphics textGraphics,TextColor color) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(color);
        textGraphics.fillRectangle(new TerminalPosition(50, 21),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(63, 22), "START");
    }

    private void drawHowToPlayButton(TextGraphics textGraphics,TextColor color) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(color);
        textGraphics.fillRectangle(new TerminalPosition(50, 25),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(60, 26), "HOW TO PLAY");
    }

    public void clear() {
        screen.clear();
    }

    public void refresh() throws IOException {
        screen.refresh();
    }

    public TextGraphics newTextGraphics() {
        return this.screen.newTextGraphics();
    }
}
