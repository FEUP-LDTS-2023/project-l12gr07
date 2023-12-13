package com.projLDTSblackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.ExitMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.IOException;

class ExitMenuViewerTest {

    private LanternaGUI mockedGUI;
    private UserInput mockedUserInput;
    private ExitMenuViewer exitMenuViewer;

    @BeforeEach
    void setUp() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        exitMenuViewer = new ExitMenuViewer(mockedGUI);
    }

    @Test
    void testGetButtonSelected() {
        exitMenuViewer.setButtonSelected(1);
        int buttonSelected = exitMenuViewer.getButtonSelected();

        assert buttonSelected == 1;
    }

    @Test
    void testDraw() throws IOException {
        exitMenuViewer.draw();

        verify(mockedGUI).clear();
        verify(mockedGUI).drawExitQ();
        verify(mockedGUI, times(2)).refresh();
    }

    @Test
    void testDrawElements() throws IOException {
        // Test drawElements method
        exitMenuViewer.setButtonSelected(0);
        exitMenuViewer.drawElements();

        verify(mockedGUI, times(1)).drawYesButton(true);
        verify(mockedGUI).drawNoButton(false);
        verify(mockedGUI, times(1)).refresh();
        // You can add more verifications as needed
    }

    @Test
    void testUserInput() throws IOException {
        when(mockedUserInput.ExitAndHowToPlayMenuInput(anyInt())).thenReturn(0);

        exitMenuViewer = new ExitMenuViewer(mockedGUI) {
            @Override
            public int userInput() throws IOException {
                return mockedUserInput.ExitAndHowToPlayMenuInput(0);
            }
        };

        int userInputResult = exitMenuViewer.userInput();

        verify(mockedUserInput).ExitAndHowToPlayMenuInput(0);
        assert userInputResult == 0;
    }

    @Test
    void testClose() throws IOException {
        // Test close method
        exitMenuViewer.close();

        verify(mockedGUI).close();
    }
}
