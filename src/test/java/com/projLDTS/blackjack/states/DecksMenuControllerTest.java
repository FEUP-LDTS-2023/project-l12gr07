package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.DecksMenuController;
import com.projLDTS.blackjack.states.GameController;
import com.projLDTS.blackjack.viewer.menus.DecksMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DecksMenuControllerTest {

    @Mock
    private ApplicationStateController mockApplicationStateController;

    @Mock
    private GameController gameController;

    private DecksMenuController decksMenuController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        decksMenuController = new DecksMenuController(mockApplicationStateController);
    }

    @Test
    public void runShouldChangeStateWhenInputIs4() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(4);
        when(mockApplicationStateController.getStateController()).thenReturn(gameController);

        decksMenuController.run();

        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }
    @Test
    void runShouldSetButtonSelectedWhenInputIsNot4() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(1, 4);
        when(mockApplicationStateController.getStateController()).thenReturn(gameController);

        decksMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }

    @Test
    public void testNextStateOneDeck() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        DecksMenuViewer mockViewer = mock(DecksMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(0);
        when(mockController.getStateController()).thenReturn(gameController);

        DecksMenuController controller = new DecksMenuController(mockController);

        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(mockController, times(1)).changeState(ApplicationState.Game);
        verify(gameController).setGameType(1);
    }
    @Test
    public void testNextStateTwoDeck() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        DecksMenuViewer mockViewer = mock(DecksMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(1);
        when(mockController.getStateController()).thenReturn(gameController);

        DecksMenuController controller = new DecksMenuController(mockController);

        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(mockController, times(1)).changeState(ApplicationState.Game);
        verify(gameController).setGameType(2);
    }
    @Test
    public void testNextStateInfDeck() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        DecksMenuViewer mockViewer = mock(DecksMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(2);
        when(mockController.getStateController()).thenReturn(gameController);

        DecksMenuController controller = new DecksMenuController(mockController);

        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(mockController, times(1)).changeState(ApplicationState.Game);
        verify(gameController).setGameType(0);
    }

    @Test
    public void testNextStateRet() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        DecksMenuViewer mockViewer = mock(DecksMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(3);

        DecksMenuController controller = new DecksMenuController(mockController);

        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(mockController, times(1)).changeState(ApplicationState.MainMenu);
    }

    @Test
    void testGetButtonSelected() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.getButtonSelected()).thenReturn(1);

        DecksMenuController controller = new DecksMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }
    @Test
    void testSetButtonSelected() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        DecksMenuController controller = new DecksMenuController(mockController);

        controller.setButtonSelected(0);

        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput()throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        DecksMenuController controller = new DecksMenuController(mockController);

        assertEquals(3, controller.userInput());

        verify(mockController, times(1)).userInput();
    }
}
