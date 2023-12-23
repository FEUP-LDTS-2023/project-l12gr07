package com.projLDTSblackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.Last10GamesMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class Last10GamesMenuViewerTest {

    private LanternaGUI mockedGUI;
    private UserInput mockedUserInput;
    private Last10GamesMenuViewer last10GamesMenuViewer;

    @BeforeEach
    void setUp() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        last10GamesMenuViewer = new Last10GamesMenuViewer(mockedGUI);
    }

    @Test
    void testGetButtonSelected() {
        last10GamesMenuViewer.setButtonSelected(2);
        int buttonSelected = last10GamesMenuViewer.getButtonSelected();

        assert buttonSelected == 2;
    }

    @Test
    void testDraw() throws IOException {
        last10GamesMenuViewer.draw();

        verify(mockedGUI).clear();
        verify(mockedGUI).drawLast10GamesText();
        verify(mockedGUI, times(1)).drawRetDecks(anyBoolean());
        verify(mockedGUI, times(2)).refresh();
    }

    @Test
    void testDrawElements() throws IOException {
        last10GamesMenuViewer.setButtonSelected(1);
        last10GamesMenuViewer.drawElements();

        verify(mockedGUI, times(1)).drawRetDecks(false);
        verify(mockedGUI, times(1)).refresh();
    }

    @Test
    void testUserInput() throws IOException {
        when(mockedUserInput.Last10GamesMenuInput(anyInt())).thenReturn(0);

        last10GamesMenuViewer = new Last10GamesMenuViewer(mockedGUI) {
            @Override
            public int userInput() throws IOException {
                return mockedUserInput.Last10GamesMenuInput(0);
            }
        };

        int userInputResult = last10GamesMenuViewer.userInput();

        verify(mockedUserInput).Last10GamesMenuInput(0);
        assert userInputResult == 0;
    }

    @Test
    void testClose() throws IOException {
        last10GamesMenuViewer.close();

        verify(mockedGUI).close();
    }
}
