package com.projLDTSblackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.MainMenuController;
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
import static org.mockito.Mockito.times;

public class MainMenuControllerTest {

    @Mock
    private ApplicationStateController mockApplicationStateController;

    private MainMenuController mainMenuController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mainMenuController = new MainMenuController(mockApplicationStateController);
    }

    @Test
    public void runShouldChangeStateWhenInputIs3() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(3);

        mainMenuController.run();

        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }
    @Test
    void runShouldSetButtonSelectedWhenInputIsNot4() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(1, 3);

        mainMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }

    @Test
    public void testNextStateStart() throws Exception {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(0);

        mainMenuController.nextState();

        verify(mockApplicationStateController).changeState(ApplicationState.StartMenu);
    }
    @Test
    public void testNextStateHowToPlay() throws Exception {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(1);

        mainMenuController.nextState();

        verify(mockApplicationStateController).changeState(ApplicationState.HowToPlay);
    }
    @Test
    public void testNextStateExit() throws Exception {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(2);

        mainMenuController.nextState();

        verify(mockApplicationStateController).changeState(ApplicationState.Exit);
    }

    @Test
    void testGetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.getButtonSelected()).thenReturn(1);

        MainMenuController controller = new MainMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }
    @Test
    void testSetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        MainMenuController controller = new MainMenuController(mockController);

        controller.setButtonSelected(0);

        // Ensure that setButtonSelected() is called on the mockController
        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        MainMenuController controller = new MainMenuController(mockController);

        assertEquals(3, controller.userInput());

        // Ensure that userInput() is called on the mockController
        verify(mockController, times(1)).userInput();
    }
}
