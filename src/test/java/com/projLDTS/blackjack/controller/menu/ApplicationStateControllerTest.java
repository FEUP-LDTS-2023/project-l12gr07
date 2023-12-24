package com.projLDTS.blackjack.controller.menu;

import com.projLDTS.blackjack.controller.menu.*;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.StartMenuController;
import com.projLDTS.blackjack.states.StateController;
import com.projLDTS.blackjack.viewer.StateViewer;
import com.projLDTS.blackjack.viewer.menus.StartMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.Mockito.*;

public class ApplicationStateControllerTest {
    private ApplicationStateController applicationStateController;
    private StateViewer mockedStateViewer;
    private StateController mockedStateController;

    @BeforeEach
    public void setup() throws IOException, FontFormatException, URISyntaxException {
        applicationStateController = new ApplicationStateController();
        mockedStateViewer = Mockito.mock(StateViewer.class);
        applicationStateController.setStateViewer(mockedStateViewer);
        mockedStateController = Mockito.mock(StateController.class);
        applicationStateController.setStateController(mockedStateController);
    }

    @Test
    public void testChangeState() throws IOException, FontFormatException, URISyntaxException {
        applicationStateController.changeState(ApplicationState.StartMenu);

        assert(applicationStateController.getStateController() instanceof StartMenuController);
        assert(applicationStateController.getStateViewer() instanceof StartMenuViewer);
    }

    @Test
    public void testRun() throws IOException, FontFormatException, URISyntaxException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            try {
                applicationStateController.run();
            } catch (IOException | FontFormatException | URISyntaxException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(2000);
        future.cancel(true);

        verify(mockedStateViewer, atLeastOnce()).draw();
        verify(mockedStateController, atLeastOnce()).run();
    }

    @Test
    public void testRedraw() throws IOException {
        StateViewer mockedStateViewer = Mockito.mock(StateViewer.class);
        applicationStateController.setStateViewer(mockedStateViewer);

        applicationStateController.redraw();

        verify(mockedStateViewer, times(1)).draw();
    }

    @Test
    public void testUserInput() throws IOException {
        when(applicationStateController.getStateViewer().userInput()).thenReturn(1);

        int userInputResult = applicationStateController.userInput();
        verify(applicationStateController.getStateViewer()).userInput();

        assertEquals(1, userInputResult);
    }

    @Test
    public void testClose() throws IOException {
        applicationStateController.close();
        verify(applicationStateController.getStateViewer()).close();
    }

    @Test
    public void testSetButtonSelected() {
        applicationStateController.setButtonSelected(2);

        verify(applicationStateController.getStateViewer()).setButtonSelected(2);
    }

    @Test
    public void testGetButtonSelected() {
        when(applicationStateController.getStateViewer().getButtonSelected()).thenReturn(3);

        int selectedButton = applicationStateController.getButtonSelected();
        verify(applicationStateController.getStateViewer()).getButtonSelected();

        assertEquals(3, selectedButton);
    }
}
