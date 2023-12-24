package com.projLDTS.blackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.StartMenuController;
import com.projLDTS.blackjack.viewer.menus.StartMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StartMenuControllerTest {

    @Mock
    private ApplicationStateController mockApplicationStateController;

    private StartMenuController startMenuController;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        startMenuController = new StartMenuController(mockApplicationStateController);
    }

    @Test
    void runShouldChangeStateWhenInputIs3() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(3);

        startMenuController.run();

        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }

    @Test
    void runShouldSetButtonSelectedWhenInputIsNot3() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(1, 3);

        startMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }

    @Test
    void testnextStateReturn() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        StartMenuViewer mockViewer = mock(StartMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(0);

        StartMenuController controller = new StartMenuController(mockController);

        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(mockController, times(1)).changeState(ApplicationState.MainMenu);
    }

    @Test
    void testnextStateButtonPlay() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        StartMenuViewer mockViewer = mock(StartMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(1);

        StartMenuController controller = new StartMenuController(mockController);

        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(mockController, times(1)).changeState(ApplicationState.DecksMenu);
    }

    @Test
    void testnextStateLast10Games() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        StartMenuViewer mockViewer = mock(StartMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(2);

        StartMenuController controller = new StartMenuController(mockController);

        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(mockController, times(1)).changeState(ApplicationState.Last10Games);
    }
    @Test
    void testGetButtonSelected() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.getButtonSelected()).thenReturn(1);

        StartMenuController controller = new StartMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }

    @Test
    void testSetButtonSelected() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        StartMenuController controller = new StartMenuController(mockController);

        controller.setButtonSelected(0);

        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        StartMenuController controller = new StartMenuController(mockController);

        assertEquals(3, controller.userInput());

        verify(mockController, times(1)).userInput();
    }
}
