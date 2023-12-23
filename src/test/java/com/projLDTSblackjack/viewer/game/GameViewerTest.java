package com.projLDTSblackjack.viewer.game;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.viewer.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameViewerTest {

    private LanternaGUI mockedGUI;
    private UserInput mockedUserInput;

    private GameViewer gameViewer;

    @BeforeEach
    void setUp() {
        mockedGUI = Mockito.mock(LanternaGUI.class);
        mockedUserInput = Mockito.mock(UserInput.class);
        gameViewer = new GameViewer(mockedGUI);
    }

    @Test
    void testGetButtonSelected() {
        gameViewer.setButtonSelected(2);
        int buttonSelected = gameViewer.getButtonSelected();

        assert buttonSelected == 2;
    }

    // TODO: não está a passar
    @Test
    void testDraw() throws IOException {
        gameViewer.draw();

        verify(mockedGUI).clear();
        verify(mockedGUI).drawExit();
        verify(mockedGUI).drawCredit();
        verify(mockedGUI).drawLine();
        verify(mockedGUI).drawBet();
        verify(mockedGUI, times(1)).drawHitButton(anyBoolean());
        verify(mockedGUI, times(1)).drawStandButton(anyBoolean());
        verify(mockedGUI, times(1)).drawDoubleDownButton(anyBoolean());
        verify(mockedGUI, times(2)).refresh();
    }

    @Test
    void testSaveGameCSV() {
        gameViewer.saveGameCSV("UsernameTest", 1, 10);

        String aux = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/last10games.csv"))) {
            while (reader.readLine() != null) {
                aux = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String orig = "UsernameTest .............................. +10";
        assertEquals(orig, aux);
    }

    // TODO: não está a passar
    @Test
    void testDrawElements() throws IOException {
        gameViewer.setButtonSelected(0);
        gameViewer.drawElements();

        verify(mockedGUI, times(1)).drawHitButton(true);
        verify(mockedGUI, times(1)).drawStandButton(false);
        verify(mockedGUI, times(1)).drawDoubleDownButton(false);
        verify(mockedGUI, times(1)).refresh();
    }

    @Test
    void testUserInput() throws IOException {
        when(mockedUserInput.GameInput(anyInt())).thenReturn(0);

        gameViewer = new GameViewer(mockedGUI) {
            @Override
            public int userInput() throws IOException {
                return mockedUserInput.GameInput(0);
            }
        };

        int userInputResult = gameViewer.userInput();

        verify(mockedUserInput).GameInput(0);
        assert userInputResult == 0;
    }

    @Test
    void testClose() throws IOException {
        gameViewer.close();

        verify(mockedGUI).close();
    }
}
