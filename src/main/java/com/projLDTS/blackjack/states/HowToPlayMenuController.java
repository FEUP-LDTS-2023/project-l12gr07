package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import com.projLDTS.blackjack.viewer.menus.HowToPlayMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class HowToPlayMenuController implements StateController {
    private final ApplicationStateController applicationStateController;

    public HowToPlayMenuController(ApplicationStateController applicationStateController_) {
        applicationStateController = applicationStateController_;
    }

    @Override
    public void run() throws IOException, FontFormatException, URISyntaxException {
        while (true) {
            int aux = userInput();
            if (aux == 1 && getPage() == 3) {
                nextState();
                break;
            }
            else if (aux == 0 && getPage() == 0) {
                nextState();
                break;
            }
            else if (aux != -1) {
                setButtonSelected(aux);
                if (getPage() > 0 && getButtonSelected() == 0)
                    setPage(getPage() - 1);
                else if (getPage() < 3 && getButtonSelected() == 1)
                    setPage(getPage() + 1);
            }
            applicationStateController.redraw();
        }
    }

    public int getPage() {
        HowToPlayMenuViewer howToPlayMenuViewer = (HowToPlayMenuViewer) applicationStateController.getStateViewer();
        return howToPlayMenuViewer.getPage();
    }

    public void setPage(int i) {
        HowToPlayMenuViewer howToPlayMenuViewer = (HowToPlayMenuViewer) applicationStateController.getStateViewer();
        howToPlayMenuViewer.setPage(i);
    }

    @Override
    public void nextState() throws IOException, FontFormatException, URISyntaxException {
        MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_CLICK);
        applicationStateController.changeState(ApplicationState.MainMenu);
    }

    @Override
    public int getButtonSelected() {
        return applicationStateController.getButtonSelected();
    }

    @Override
    public void setButtonSelected(int i) {
        applicationStateController.setButtonSelected(i);
    }

    @Override
    public int userInput() throws IOException {
        return applicationStateController.userInput();
    }
}
