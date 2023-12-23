package com.projLDTSblackjack.viewer.menus;

import com.googlecode.lanterna.screen.Screen;
import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.StartMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StartMenuViewerTest {
    private LanternaGUI mockedGUI;
    private UserInput mockedUserInput;
    private StartMenuViewer startMenuViewer;

    @BeforeEach
    public void setUp() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        startMenuViewer = new StartMenuViewer(mockedGUI);

        Screen mockedScreen = Mockito.mock(Screen.class);
        when(mockedGUI.getScreen()).thenReturn(mockedScreen);
    }
    @Test
    void testGetButtonSelected() {
        startMenuViewer.setButtonSelected(2);
        int buttonSelected = startMenuViewer.getButtonSelected();

        assert buttonSelected == 2;
    }

    @Test
    public void testDraw() throws IOException {
        String testUsername = "testUsername";
        UserInput.setUsername(testUsername);

        startMenuViewer.draw();

        ArgumentCaptor<StringBuilder> usernameCaptor = ArgumentCaptor.forClass(StringBuilder.class);

        InOrder inOrder = inOrder(mockedGUI);
        inOrder.verify(mockedGUI).clear();
        inOrder.verify(mockedGUI).drawBlackjack();
        inOrder.verify(mockedGUI).drawGetUsername(usernameCaptor.capture());
        inOrder.verify(mockedGUI).drawLast10GamesButton(false);
        inOrder.verify(mockedGUI).drawbReturnButton(true);

        if (!testUsername.isEmpty()) {
            inOrder.verify(mockedGUI).drawPlayButton(false);
        }

        inOrder.verify(mockedGUI, times(2)).refresh();

        assertEquals(testUsername, usernameCaptor.getValue().toString());
    }


    @Test
    public void testDrawElements() throws IOException {
        startMenuViewer.setButtonSelected(2);
        startMenuViewer.drawElements();

        verify(mockedGUI).drawLast10GamesButton(true);
        verify(mockedGUI).drawbReturnButton(false);
        verify(mockedGUI).drawPlayButton(false);
    }

    @Test
    public void testUserInput() throws IOException {
        when(mockedUserInput.StartMenuInput(anyInt(), any(StringBuilder.class))).thenReturn(1);
        UserInput.setUsername("Stringggg");

        startMenuViewer = new StartMenuViewer(mockedUserInput, mockedGUI);

        int result = startMenuViewer.userInput();
        verify(mockedUserInput, times(1)).StartMenuInput(anyInt(), any(StringBuilder.class));
        assertEquals(1, result);
    }

    @Test
    public void testClose() throws IOException {
        startMenuViewer.close();
        verify(mockedGUI).close();
    }
}
