package com.projLDTSblackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.MainMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MainMenuViewerTest {

    private LanternaGUI mockedGUI;
    private UserInput mockedUserInput;
    private MainMenuViewer mainMenuViewer;

    @BeforeEach
    void setUp() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        mainMenuViewer = new MainMenuViewer(mockedGUI);
    }

    @Test
    void testGetButtonSelected() {
        mainMenuViewer.setButtonSelected(2);
        int buttonSelected = mainMenuViewer.getButtonSelected();

        assert buttonSelected == 2;
    }

    @Test
    void testDraw() throws IOException {
        mainMenuViewer.draw();

        verify(mockedGUI).clear();
        verify(mockedGUI).drawBlackjack();
        verify(mockedGUI, times(1)).drawStartButton(anyBoolean());
        verify(mockedGUI, times(1)).drawHowToPlayButton(anyBoolean());
        verify(mockedGUI, times(1)).drawExitButton(anyBoolean());
        verify(mockedGUI, times(2)).refresh();
    }

    @Test
    void testDrawElements() throws IOException {
        mainMenuViewer.setButtonSelected(1);
        mainMenuViewer.drawElements();

        verify(mockedGUI, times(1)).drawStartButton(false);
        verify(mockedGUI, times(1)).drawHowToPlayButton(true);
        verify(mockedGUI, times(1)).drawExitButton(false);
        verify(mockedGUI, times(1)).refresh();
    }

    @Test
    void testUserInput() throws IOException {
        when(mockedUserInput.MainMenuInput(anyInt())).thenReturn(0);

        mainMenuViewer = new MainMenuViewer(mockedGUI) {
            @Override
            public int userInput() throws IOException {
                return mockedUserInput.MainMenuInput(0);
            }
        };

        int userInputResult = mainMenuViewer.userInput();

        verify(mockedUserInput).MainMenuInput(0);
        assert userInputResult == 0;
    }

    @Test
    void testClose() throws IOException {
        mainMenuViewer.close();

        verify(mockedGUI).close();
    }
}
