package com.projLDTSblackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.ExitMenuController;
import com.projLDTS.blackjack.viewer.menus.ExitMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExitMenuControllerTest {

    @Mock
    private ApplicationStateController mockApplicationStateController;

    private ExitMenuController exitMenuController;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        exitMenuController = new ExitMenuController(mockApplicationStateController);
    }

    @Test
    void runShouldChangeStateWhenInputIs2() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(2);
        exitMenuController.setTest(true);

        exitMenuController.run();

        verify(mockApplicationStateController, times(1)).close();
    }

    @Test
    void runShouldSetButtonSelectedWhenInputIsNot2() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(1, 2);
        exitMenuController.setTest(true);

        exitMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }

    @Test
    void testnextStateExit() throws Exception {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(0);
        exitMenuController.setTest(true);

        exitMenuController.nextState();

        verify(mockApplicationStateController, times(1)).close();
    }

    @Test
    void testnextStateMainMenu() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        ExitMenuViewer mockViewer = mock(ExitMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(1);

        ExitMenuController controller = new ExitMenuController(mockController);

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

        ExitMenuController controller = new ExitMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }

    @Test
    void testSetButtonSelected() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        ExitMenuController controller = new ExitMenuController(mockController);

        controller.setButtonSelected(0);

        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        ExitMenuController controller = new ExitMenuController(mockController);

        assertEquals(3, controller.userInput());

        verify(mockController, times(1)).userInput();
    }

}
