package com.projLDTS.blackjack.states;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface StateController {
    void run() throws IOException, FontFormatException, URISyntaxException;

    void nextState() throws IOException, FontFormatException, URISyntaxException;

}