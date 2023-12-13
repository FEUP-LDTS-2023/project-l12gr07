package com.projLDTSblackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.HowToPlayMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class HowToPlayMenuViewerTest {

    private LanternaGUI mockedGUI;

    private UserInput mockedUserInput;

    private HowToPlayMenuViewer howToPlayMenuViewer;

    @BeforeEach
    void setUp() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        howToPlayMenuViewer = new HowToPlayMenuViewer(mockedGUI);
    }

    @Test
    void testDraw() throws IOException {
        howToPlayMenuViewer.draw();

        verify(mockedGUI).clear();
        verify(mockedGUI, times(4)).refresh();
    }

    @Test
    void testUserInput() throws IOException {
        when(mockedUserInput.ExitAndHowToPlayMenuInput(anyInt())).thenReturn(1);

        howToPlayMenuViewer = new HowToPlayMenuViewer(mockedGUI) {
            @Override
            public int userInput() throws IOException {
                return mockedUserInput.ExitAndHowToPlayMenuInput(0);
            }
        };

        int userInputResult = howToPlayMenuViewer.userInput();

        verify(mockedUserInput).ExitAndHowToPlayMenuInput(0);
        assert userInputResult == 1;
    }

    @Test
    void testGetButtonSelected() {
        howToPlayMenuViewer.setButtonSelected(2);
        int buttonSelected = howToPlayMenuViewer.getButtonSelected();

        assert buttonSelected == 2;
    }

    @Test
    void testSetPage() {
        howToPlayMenuViewer.setPage(1);
        int page = howToPlayMenuViewer.getPage();

        assert page == 1;
    }

    @Test
    void testDrawPageContent() throws IOException {
        howToPlayMenuViewer.setPage(0);
        howToPlayMenuViewer.drawPageContent();

        verify(mockedGUI).refresh();
    }

    @Test
    void testDrawPageButtons() throws IOException {
        howToPlayMenuViewer.setPage(1);
        howToPlayMenuViewer.drawPageButtons();

        verify(mockedGUI).refresh();
    }
}
