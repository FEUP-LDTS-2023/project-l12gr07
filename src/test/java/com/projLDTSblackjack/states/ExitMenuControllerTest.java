package com.projLDTSblackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.ExitMenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExitMenuControllerTest {

    @Mock
    private ApplicationStateController mockApplicationStateController;

    private ExitMenuController exitMenuController;


    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        MockitoAnnotations.initMocks(this);
        exitMenuController = new ExitMenuController(mockApplicationStateController);
    }

    //TODO : N FUNCIONA
    /*@Test
    void runShouldChangeStateWhenInputIs2() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(2);

        exitMenuController.run();

        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }*/

    //TODO : N FUNCIONA
    /*@Test
    void runShouldSetButtonSelectedWhenInputIsNot2() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(1, 2);

        exitMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockApplicationStateController, times(2)).userInput();
    }*/

    //TODO : N FUNCIONA
    /*@Test
    void testnextStateExit() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(0);

        exitMenuController.nextState();

        verify(mockApplicationStateController, times(1)).close();
    }*/

    @Test
    void testnextStateMainMenu() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.getButtonSelected()).thenReturn(1);

        exitMenuController.nextState();

        verify(mockApplicationStateController, times(1)).changeState(ApplicationState.MainMenu);
    }

    @Test
    void testGetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.getButtonSelected()).thenReturn(1);

        ExitMenuController controller = new ExitMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }

    @Test
    void testSetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        ExitMenuController controller = new ExitMenuController(mockController);

        controller.setButtonSelected(0);

        // Ensure that setButtonSelected() is called on the mockController
        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        ExitMenuController controller = new ExitMenuController(mockController);

        assertEquals(3, controller.userInput());

        // Ensure that userInput() is called on the mockController
        verify(mockController, times(1)).userInput();
    }

}
