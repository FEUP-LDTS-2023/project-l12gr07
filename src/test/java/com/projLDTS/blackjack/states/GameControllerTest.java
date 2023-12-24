package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import com.projLDTS.blackjack.model.game.Decks.GameSet;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.GameController;
import com.projLDTS.blackjack.states.StartMenuController;
import com.projLDTS.blackjack.viewer.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    @Mock
    private ApplicationStateController mockApplicationStateController;

    @Mock
    private GameViewer mockGameViewer;

    private GameController gameController;
    private GameSet gameSet;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gameController = new GameController(mockApplicationStateController);
        when(mockApplicationStateController.getStateViewer()).thenReturn(mockGameViewer);
    }

    @Test
    void runShouldChangeStateWhenInputIs5() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(5);
        gameController.run();
        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }

    @Test
    void runShouldSetButtonSelectedWhenInputIsNot5() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(2, 5);
        gameController.run();
        verify(mockApplicationStateController, atLeastOnce()).setButtonSelected(anyInt());
        verify(mockApplicationStateController, atLeastOnce()).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }

    @Test
    void testGetButtonSelected() throws Exception {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(1);
        assertEquals(1, gameController.getButtonSelected());
        verify(mockApplicationStateController, times(1)).getButtonSelected();
    }

    @Test
    void testSetButtonSelected() throws Exception {
        gameController.setButtonSelected(0);
        verify(mockApplicationStateController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(3);
        assertEquals(3, gameController.userInput());
        verify(mockApplicationStateController, times(1)).userInput();
    }

    @Test
    void testSetGameType() {
        int gameType = 1;
        gameController.setGameType(gameType);
        assertEquals(gameType, gameController.gameType);
    }

}
