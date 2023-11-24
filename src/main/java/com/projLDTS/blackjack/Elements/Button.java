package com.projLDTS.blackjack.Elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Button {
    private final String str;
    private final TerminalSize size;
    private final TerminalPosition pos;
    private TextColor color;
    private final TerminalPosition stringPos;

    public Button(TerminalPosition stringPos_, String str_, TerminalSize size_, TerminalPosition pos_, TextColor color_) {
        str = str_;
        size = size_;
        pos = pos_;
        color = color_;
        stringPos = stringPos_;
    }

    public void drawButton(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(color);
        textGraphics.fillRectangle(pos,size,' ');
        textGraphics.putString(stringPos, str);
    }

    public void setColor(TextColor color_) {color = color_;};
}
