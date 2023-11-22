package com.projLDTS.blackjack;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Board {
    private int width;
    private int height;
    public Board (int w, int h) {
        setWidth(w);
        setHeight(h);
    }

    private void setHeight(int h) {
        height = h;
    }

    private void setWidth(int w) {
        width = w;
    }

    public void draw(TextGraphics textGraphics) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }
}