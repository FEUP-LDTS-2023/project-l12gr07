package com.projLDTS.blackjack.viewer;

import java.io.IOException;
import com.projLDTS.blackjack.gui.LanternaGUI;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(LanternaGUI gui) throws IOException;

    protected abstract void drawElements(LanternaGUI gui) throws IOException;
}
