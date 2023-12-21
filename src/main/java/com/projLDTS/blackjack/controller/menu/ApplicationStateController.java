package com.projLDTS.blackjack.controller.menu;

import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.states.*;
import com.projLDTS.blackjack.viewer.StateViewer;
import com.projLDTS.blackjack.viewer.game.GameViewer;
import com.projLDTS.blackjack.viewer.menus.*;
import com.projLDTS.blackjack.controller.music.MusicOptions;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ApplicationStateController {
    private boolean run;
    private StateController stateController;
    private StateViewer stateViewer;
    private ApplicationState applicationState;
    private LanternaGUI gui;
    List last10games;

    public ApplicationStateController() throws IOException, FontFormatException, URISyntaxException {
        gui = new LanternaGUI(130, 40);
        run = true;
        changeState(ApplicationState.MainMenu);
        stateViewer = new MainMenuViewer(gui);
        MusicManager.getInstance().setBackgroundSound(MusicOptions.BACKGROUND_MUSIC);
        //readFromCSV();
    }

    public StateController getStateController() {
        return stateController;
    }

    public StateViewer getStateViewer(){return stateViewer;}

    public void setGui(LanternaGUI lanternaGUI) {
        gui = lanternaGUI;
    }

    public void run() throws IOException, FontFormatException, URISyntaxException, InterruptedException {
        while (run) {
            stateViewer.draw();
            stateController.run();
        }
    }

    public void stop() {
        run = false;
    }

    public void redraw() throws IOException {
        stateViewer.draw();
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void changeState(ApplicationState state) throws IOException, FontFormatException, URISyntaxException {
        applicationState = state;
        switch (state) {
            case Game:
                stateController = new GameController(this);
                stateViewer = new GameViewer(gui);
                break;

            case MainMenu:
                stateController = new MainMenuController(this);
                stateViewer = new MainMenuViewer(gui);
                //setToCSV();
                break;

            case HowToPlay:
                stateController = new HowToPlayMenuController(this);
                stateViewer = new HowToPlayMenuViewer(gui);
                break;

            case StartMenu:
                stateController = new StartMenuController(this);
                stateViewer = new StartMenuViewer(gui);
                break;

            case DecksMenu:
                stateController = new DecksMenuController(this);
                stateViewer = new DecksMenuViewer(gui);
                break;

            case Last10Games:
                stateController = new Last10GamesMenuController(this);
                stateViewer = new Last10GamesMenuViewer(gui);
                break;

            case Exit:
                stateController = new ExitMenuController(this);
                stateViewer = new ExitMenuViewer(gui);
        }
    }

    public void close() throws IOException {
        stateViewer.close();
    }

    public int userInput() throws IOException {
        return stateViewer.userInput();
    }

    public void setButtonSelected(int i) {
        stateViewer.setButtonSelected(i);
    }

    public int getButtonSelected() {
        return stateViewer.getButtonSelected();
    }

//    private void readFromCSV() throws FileNotFoundException {
//        Scanner sc = new Scanner(new File("src/main/resources/last10games.csv"));
//        //parsing a CSV file into the constructor of Scanner class
//        sc.useDelimiter(",");
//        //setting comma as delimiter pattern
//        while (sc.hasNext()) {
//            last10games.add(sc.next());
//        }
//        sc.close();
//    }

//    private void setToCSV() {
//
//    }

//    private void addToCSV(String username, String value) {
//        String result = username + ";" + value;
//        last10games.add(result);
//        if (last10games.size() == 11) {
//            last10games.remove(0);
//        }
//    }

    // for tests
    public void setStateViewer(StateViewer stateViewer_) {
        stateViewer = stateViewer_;
    }

    public void setStateController(StateController stateController_) {
        stateController = stateController_;
    }
}
