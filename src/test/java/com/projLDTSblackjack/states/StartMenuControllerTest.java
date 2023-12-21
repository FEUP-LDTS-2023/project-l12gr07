package com.projLDTSblackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.StartMenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class StartMenuControllerTest {

    @Mock
    private ApplicationStateController mockApplicationStateController;

    private StartMenuController startMenuController;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        MockitoAnnotations.initMocks(this);
        startMenuController = new StartMenuController(mockApplicationStateController);
    }

    @Test
    void runShouldChangeStateWhenInputIs3() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(3);

        startMenuController.run();

        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }

    @Test
    void runShouldSetButtonSelectedWhenInputIsNot3() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(1, 3);

        startMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }

    @Test
    void nextStateShouldChangeStateAccordingToButtonSelected() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(0);

        startMenuController.nextState();

        verify(mockApplicationStateController, times(1)).changeState(ApplicationState.MainMenu);
    }

    @Test
    void nextStateShouldChangeStateAccordingToButtonSelected2() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(1);

        startMenuController.nextState();

        verify(mockApplicationStateController, times(1)).changeState(ApplicationState.DecksMenu);
    }

    @Test
    void nextStateShouldChangeStateAccordingToButtonSelected3() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(2);

        startMenuController.nextState();

        verify(mockApplicationStateController, times(1)).changeState(ApplicationState.Last10Games);
    }
}
