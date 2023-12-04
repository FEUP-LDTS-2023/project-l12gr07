package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.HowToPlayMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class HowToPlayMenuController implements StateController {
    private final ApplicationStateController applicationStateController;
    private HowToPlayMenuViewer howToPlayMenuViewer;
    private final LanternaGUI gui;

    public HowToPlayMenuController(ApplicationStateController applicationStateController_) throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = applicationStateController_;
        gui = new LanternaGUI(130, 40);
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        howToPlayMenuViewer = new HowToPlayMenuViewer(gui);
        howToPlayMenuViewer.draw();
        while (true) {
            int aux = new UserInput(gui).ExitAndHowToPlayMenuInput(howToPlayMenuViewer.getButtonSelected());
            if (aux == 1 && howToPlayMenuViewer.getPage() == 3) {
                nextState();
                break;
            }
            else if (aux == 0 && howToPlayMenuViewer.getPage() == 0) {
                nextState();
                break;
            }
            else if (aux != -1) {
                howToPlayMenuViewer.setButtonSelected(aux);
                if (howToPlayMenuViewer.getPage() > 0 && howToPlayMenuViewer.getButtonSelected() == 0)
                    howToPlayMenuViewer.setPage(howToPlayMenuViewer.getPage() - 1);
                else if (howToPlayMenuViewer.getPage() < 3 && howToPlayMenuViewer.getButtonSelected() == 1)
                    howToPlayMenuViewer.setPage(howToPlayMenuViewer.getPage() + 1);
            }
            howToPlayMenuViewer.draw();
        }
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        applicationStateController.changeState(ApplicationState.MainMenu);
        gui.close();
    }
}
