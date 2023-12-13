package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;


import java.io.IOException;

public class HowToPlayMenuViewer implements StateViewer {
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
        gui.refresh();
    }

    public void drawPageButtons() throws IOException {
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
    public void drawPageContent() throws IOException {
        switch (page) {
            case 0:
                gui.drawHowToPlayPage1();
                break;
            case 1:
                gui.drawHowToPlayPage2();
                break;
            case 2:
                gui.drawHowToPlayPage3();
                break;
            case 3:
                gui.drawHowToPlayPage4();
                break;
            default:
                break;
        }
        gui.refresh();
    }

    @Override
    public int userInput() throws IOException {
        return new UserInput(gui).ExitAndHowToPlayMenuInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }
}
