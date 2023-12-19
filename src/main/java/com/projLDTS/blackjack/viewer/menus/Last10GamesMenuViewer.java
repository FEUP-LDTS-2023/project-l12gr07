package com.projLDTS.blackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.StateViewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Last10GamesMenuViewer implements StateViewer {
    int buttonSelected;
    private final LanternaGUI gui;
    List last10games;

    public Last10GamesMenuViewer(LanternaGUI gui_){
        buttonSelected = 0;
        gui = gui_;
        this.last10games = last10games;
    }

    public int getButtonSelected() {
        return buttonSelected;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawLast10GamesText();
        List<String> last10Lines = loadLast10LinesFromCSV();
        gui.drawLast10Lines(last10Lines);
        drawElements();
        gui.refresh();
    }

    @Override
    public void drawElements() throws IOException {
        gui.drawRetDecks(buttonSelected == 0);
        gui.refresh();
    }


    public void setButtonSelected(int i) {
        buttonSelected = i;
    }

    @Override
    public int userInput() throws IOException {
        return new UserInput(gui).Last10GamesMenuInput(buttonSelected);
    }

    @Override
    public void close() throws IOException {
        gui.close();
    }
    private List<String> loadLast10LinesFromCSV() {
        List<String> last10Lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/last10games.csv"))) {
            String line;
            LinkedList<String> lines = new LinkedList<>();
            // Read all lines and keep only the last 10 in the list
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                if (lines.size() > 10) {
                    lines.removeFirst();
                }
            }

            last10Lines.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return last10Lines;
    }
}
