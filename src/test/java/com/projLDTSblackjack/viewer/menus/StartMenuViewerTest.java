package com.projLDTSblackjack.viewer.menus;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.menus.DecksMenuViewer;
import com.projLDTS.blackjack.viewer.menus.StartMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StartMenuViewerTest {
    private LanternaGUI mockedGUI;
    //@Mock
    private UserInput mockedUserInput;

    //@InjectMocks
    private StartMenuViewer startMenuViewer;

    @BeforeEach
    public void setUp() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        startMenuViewer = new StartMenuViewer(mockedGUI);
    }

    @Test
    public void testDraw() throws Exception {
        LanternaGUI mockedGUI = mock(LanternaGUI.class);
        StartMenuViewer startMenuViewer = new StartMenuViewer(mockedGUI);
        String testUsername = "testUsername";
        startMenuViewer.setUsername(testUsername);

        startMenuViewer.draw();

        ArgumentCaptor<StringBuilder> usernameCaptor = ArgumentCaptor.forClass(StringBuilder.class);

        InOrder inOrder = inOrder(mockedGUI);
        inOrder.verify(mockedGUI,times(1)).clear();
        inOrder.verify(mockedGUI,times(1)).drawBlackjack();
        inOrder.verify(mockedGUI,times(1)).drawGetUsername(usernameCaptor.capture());
        inOrder.verify(mockedGUI,times(1)).drawLast10GamesButton(false);
        inOrder.verify(mockedGUI,times(1)).drawbReturnButton(false);
        inOrder.verify(mockedGUI,times(1)).drawPlayButton(false);
        inOrder.verify(mockedGUI,times(2)).refresh();

        assertEquals(testUsername, usernameCaptor.getValue().toString());

    }

    @Test
    public void testDrawElements() throws Exception {
        startMenuViewer.drawElements();

        verify(mockedGUI).drawLast10GamesButton(false);
        verify(mockedGUI).drawbReturnButton(true);
        verify(mockedGUI).drawPlayButton(false);
    }

    @Test
    public void testUserInput() throws Exception {
        when(mockedUserInput.StartMenuInput(anyInt(), any(StringBuilder.class))).thenReturn(1);
        startMenuViewer.setUsername("Stringggg");
        startMenuViewer = new StartMenuViewer(mockedGUI){
            @Override
            public int userInput() throws IOException {
                return mockedUserInput.StartMenuInput(1,getUsername());
            }
        };
        int userInputResult = startMenuViewer.userInput();
        verify(mockedUserInput).StartMenuInput(anyInt(), any(StringBuilder.class));
        assert(userInputResult == 1);
    }

    @Test
    public void testClose() throws Exception {
        startMenuViewer.close();
        verify(mockedGUI).close();
    }
}
