package com.projLDTS.blackjack.viewer;

import java.io.IOException;

public interface StateViewer {
    public void draw() throws IOException;
    public void drawElements() throws IOException;
    public int getButtonSelected();
    public void setButtonSelected(int i);
    int userInput() throws IOException;
    void close() throws IOException;
}
