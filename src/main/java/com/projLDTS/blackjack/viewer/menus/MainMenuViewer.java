package com.projLDTS.blackjack.viewer.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.projLDTS.blackjack.Elements.Button;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.viewer.Viewer;

import java.io.IOException;

public class MainMenuViewer extends Viewer<MainMenuViewer> {

    Button start;
    Button howToPlay;
    Button exit;
    int buttonSelected;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    TextColor selectedColor = TextColor.Factory.fromString("#FF0000");

    public MainMenuViewer(MainMenuViewer model) {
        super(model);
    }

    @Override
    public void draw(LanternaGUI gui) throws IOException {
        gui.clear();
        TextGraphics textGraphics = gui.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        gui.drawBlackjack(textGraphics);
        drawElements(gui);
    }
    @Override
    protected void drawElements(LanternaGUI gui) throws IOException {
        exit.drawButton(gui.newTextGraphics());
        start.drawButton(gui.newTextGraphics();
        howToPlay.drawButton(gui.newTextGraphics();
        gui.refresh();
    }
}
