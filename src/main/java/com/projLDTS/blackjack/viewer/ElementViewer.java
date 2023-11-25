package com.projLDTS.blackjack.viewer;

import com.projLDTS.blackjack.gui.LanternaGUI;

public interface ElementViewer<T> {
    void draw(T element, LanternaGUI gui);
}
