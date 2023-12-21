package com.projLDTSblackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.HowToPlayMenuController;
import com.projLDTS.blackjack.states.Last10GamesMenuController;
import com.projLDTS.blackjack.states.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class Last10GamesMenuControllerTest {
    @Mock
    private ApplicationStateController mockApplicationStateController;

    private Last10GamesMenuController last10GamesMenuController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        last10GamesMenuController = new Last10GamesMenuController(mockApplicationStateController);
    }

    @Test
    public void runShouldChangeStateWhenInputIs1() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(1);

        last10GamesMenuController.run();

        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }
    @Test
    void runShouldSetButtonSelectedWhenInputIsNot4() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(0, 1);

        last10GamesMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }

    @Test
    public void testNextStateRet() throws Exception {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(0);

        last10GamesMenuController.nextState();

        verify(mockApplicationStateController).changeState(ApplicationState.StartMenu);
    }

    @Test
    void testGetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.getButtonSelected()).thenReturn(1);

        Last10GamesMenuController controller = new Last10GamesMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }
    @Test
    void testSetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        Last10GamesMenuController controller = new Last10GamesMenuController(mockController);

        controller.setButtonSelected(0);

        // Ensure that setButtonSelected() is called on the mockController
        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        Last10GamesMenuController controller = new Last10GamesMenuController(mockController);

        assertEquals(3, controller.userInput());

        // Ensure that userInput() is called on the mockController
        verify(mockController, times(1)).userInput();
    }
}
