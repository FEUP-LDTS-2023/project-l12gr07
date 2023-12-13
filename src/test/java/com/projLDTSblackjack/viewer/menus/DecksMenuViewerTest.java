package com.projLDTSblackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.DecksMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class DecksMenuViewerTest {
    private LanternaGUI mockedGUI;
    private UserInput mockedUserInput;
    private DecksMenuViewer decksMenuViewer;

    @BeforeEach
    public void setup() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        decksMenuViewer = new DecksMenuViewer(mockedGUI);
    }

    @Test
    public void testDraw() throws IOException {
        decksMenuViewer.draw();

        verify(mockedGUI).clear();

        verify(mockedGUI).drawDecksText();
        verify(mockedGUI, times(1)).drawOneButton(Mockito.anyBoolean());
        verify(mockedGUI, times(1)).drawTwoButton(Mockito.anyBoolean());
        verify(mockedGUI, times(1)).drawInfButton(Mockito.anyBoolean());
        verify(mockedGUI, times(1)).drawRetDecks(Mockito.anyBoolean());

        verify(mockedGUI, times(2)).refresh();
    }

    @Test
    public void testDrawElements() throws IOException {
        decksMenuViewer.drawElements();

        verify(mockedGUI).drawDecksText();
        verify(mockedGUI).drawOneButton(true);
        verify(mockedGUI).drawTwoButton(false);
        verify(mockedGUI).drawInfButton(false);
        verify(mockedGUI).drawRetDecks(false);
    }

    @Test
    public void testSetButtonSelected() {
        decksMenuViewer.setButtonSelected(2);

        int selectedButton = decksMenuViewer.getButtonSelected();
        assert(selectedButton == 2);
    }

    @Test
    public void testUserInput() throws IOException {
        when(mockedUserInput.DecksMenuInput(1)).thenReturn(1);
        decksMenuViewer = new DecksMenuViewer(mockedGUI) {
            @Override
            public int userInput() throws IOException {
                return mockedUserInput.DecksMenuInput(1);
            }
        };
        int userInputResult = decksMenuViewer.userInput();
        verify(mockedUserInput).DecksMenuInput(1);
        assert(userInputResult == 1);
    }

    @Test
    public void testClose() throws IOException {
        decksMenuViewer.close();
        verify(mockedGUI).close();
    }
}
