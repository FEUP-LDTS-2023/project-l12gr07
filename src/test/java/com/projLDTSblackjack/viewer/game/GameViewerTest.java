package com.projLDTSblackjack.viewer.game;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import com.projLDTS.blackjack.model.game.Cards.Hand;
import com.projLDTS.blackjack.viewer.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Test
    void testDraw() throws IOException {
        UserInput.setBet(new StringBuilder("10"));
        UserInput.setBetEnded(true);
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
    void testDrawElements() throws IOException {
        UserInput.setBet(new StringBuilder("10"));
        UserInput.setBetEnded(true);
        gameViewer.setButtonSelected(0);
        gameViewer.drawElements();
        if(!UserInput.getBet().toString().isEmpty() && UserInput.getbetEnded()) {
            verify(mockedGUI, times(1)).drawHitButton(true);
            verify(mockedGUI, times(1)).drawStandButton(false);
            verify(mockedGUI, times(1)).drawDoubleDownButton(false);
            verify(mockedGUI, times(1)).refresh();
        }
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

    @Test
    void testRefreshCreditBet() {
        gameViewer.refreshCreditBet();
        verify(mockedGUI, times(1)).drawCredit();
        verify(mockedGUI, times(1)).drawBet();
    }

    @Test
    void testPlayerLost() throws IOException {
        gameViewer.playerLost();
        verify(mockedGUI).drawPlayerLost();
        verify(mockedGUI).refresh();
        verify(mockedGUI, never()).drawHitButton(anyBoolean());
        verify(mockedGUI, never()).drawStandButton(anyBoolean());
        verify(mockedGUI, never()).drawDoubleDownButton(anyBoolean());
    }

    @Test
    void testPlayerNoCredit() throws IOException {
        gameViewer.playerNoCredit();
        verify(mockedGUI).drawPlayerNoCredit();
        verify(mockedGUI).refresh();
        verify(mockedGUI, never()).drawHitButton(anyBoolean());
        verify(mockedGUI, never()).drawStandButton(anyBoolean());
        verify(mockedGUI, never()).drawDoubleDownButton(anyBoolean());
    }

    @Test
    void testPlayerWon() throws IOException {
        gameViewer.playerWon();
        verify(mockedGUI).drawPlayerWon();
        verify(mockedGUI).refresh();
        verify(mockedGUI, never()).drawHitButton(anyBoolean());
        verify(mockedGUI, never()).drawStandButton(anyBoolean());
        verify(mockedGUI, never()).drawDoubleDownButton(anyBoolean());
    }

    @Test
    void testDrawCards() {
        // TODO
    }

    @Test
    void testDrawFirstCards() {
        // TODO
    }

    @Test
    void testPlayDraw() throws IOException {
        gameViewer.playDraw();
        verify(mockedGUI).drawPlayDraw();
        verify(mockedGUI).refresh();
    }

    @Test
    void testSaveGameCSV() throws IOException {
        UserInput.setUsername("UsernameTest");
        UserInput.setGameResult(1);
        UserInput.setBetValue(10);
        UserInput.setBetEnded(true);
        gameViewer.saveGameCSV(UserInput.getUsername().toString(), UserInput.getGameResult(), UserInput.getBetValue());
    }

    @Test
    void testSetAfterPlay() {
        GameViewer gameViewer = new GameViewer(mockedGUI);
        gameViewer.setAfterPlay(true);
        assertEquals(true, gameViewer.isAfterPlay());
        gameViewer.setAfterPlay(false);
        assertEquals(false, gameViewer.isAfterPlay());
    }
}
