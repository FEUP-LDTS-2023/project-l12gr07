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

public class HowToPlayMenuViewer implements Viewer {
    int buttonSelected;
    int page;
    private final LanternaGUI gui;

    public HowToPlayMenuViewer(LanternaGUI gui_){
        page = 0;
        buttonSelected = 0;
        gui = gui_;
    }

    public int getButtonSelected() {
        return buttonSelected;
    }

    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    public void setPage(int i) {
        page = i;
    }

    public int getPage() {
        return page;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        drawPageContent();
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        drawPageButtons();
    }

    private void drawPageButtons() throws IOException {
        if (page == 0) {
            gui.drawRet1();
            gui.drawNext();
        }
        else if (page == 1) {
            gui.drawPrevious();
            gui.drawNext();
        }
        else if (page == 2) {
            gui.drawPrevious();
            gui.drawNext();
        }
        else if (page == 3) {
            gui.drawPrevious();
            gui.drawRet2();
        }
        gui.refresh();
    }
    private void drawPageContent() throws IOException {
        switch (page) {
            case 0:
                gui.clear();
                gui.drawHowToPlayPage1();
                break;
            case 1:
                gui.clear();
                gui.drawHowToPlayPage2();
                break;
            case 2:
                gui.clear();
                gui.drawHowToPlayPage3();
                break;
            case 3:
                gui.clear();
                gui.drawHowToPlayPage4();
                break;
            default:
                break;
        }
        gui.refresh();
    }
}
