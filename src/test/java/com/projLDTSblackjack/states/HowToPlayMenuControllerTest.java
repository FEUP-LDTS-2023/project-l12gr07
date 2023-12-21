package com.projLDTSblackjack.states;

import com.projLDTS.blackjack.controller.menu.ApplicationStateController;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import com.projLDTS.blackjack.states.ApplicationState;
import com.projLDTS.blackjack.states.HowToPlayMenuController;
import com.projLDTS.blackjack.viewer.menus.HowToPlayMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HowToPlayMenuControllerTest {
    @Mock
    private ApplicationStateController mockApplicationStateController;
    private HowToPlayMenuController howToPlayMenuController;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        MockitoAnnotations.initMocks(this);
        howToPlayMenuController = new HowToPlayMenuController(mockApplicationStateController);
    }


    //TODO: estes 4 testes ainda n funcionam
    /*@Test
    public void runShouldChangeStateWhenInputIs1AndPageIs3() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(1);
        when(howToPlayMenuController.getPage()).thenReturn(3);

        howToPlayMenuController.run();

        // Ensure that changeState() is called when input is 1 and page is 3
        verify(mockApplicationStateController, times(1)).changeState(ApplicationState.MainMenu);
    }


    @Test
    public void runShouldChangeStateWhenInputIs0AndPageIs0() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(0);
        when(howToPlayMenuController.getPage()).thenReturn(0);

        howToPlayMenuController.run();

        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }

    @Test
    public void runShouldSetButtonSelectedAndChangePageWhenInputIsNotMinusOne() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(2);
        when(howToPlayMenuController.getPage()).thenReturn(2);
        when(mockApplicationStateController.getButtonSelected()).thenReturn(1);

        howToPlayMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(1)).redraw();
        verify(howToPlayMenuController, times(1)).getPage();
        verify(mockApplicationStateController, times(1)).getButtonSelected();
        verify(howToPlayMenuController, times(1)).setPage(anyInt());
    }

    @Test
    public void runShouldBreakLoopWhenInputIsMinusOne() throws IOException, FontFormatException, URISyntaxException {
        when(mockApplicationStateController.userInput()).thenReturn(-1);

        howToPlayMenuController.run();

        verify(mockApplicationStateController, times(0)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(0)).redraw();
        verify(howToPlayMenuController, times(0)).getPage();
        verify(mockApplicationStateController, times(0)).getButtonSelected();
        verify(howToPlayMenuController, times(0)).setPage(anyInt());
    }*/

    @Test
    void testGetPage() throws IOException, URISyntaxException, FontFormatException {
        HowToPlayMenuViewer mockViewer = mock(HowToPlayMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        when(mockViewer.getPage()).thenReturn(2);
        assertEquals(2, controller.getPage());

        // Ensure that getStateViewer() is called
        verify(mockController, times(1)).getStateViewer();
    }

    @Test
    void testSetPage() throws IOException, URISyntaxException, FontFormatException {
        HowToPlayMenuViewer mockViewer = mock(HowToPlayMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        controller.setPage(3);

        // Ensure that setPage() is called on the mockViewer
        verify(mockViewer, times(1)).setPage(3);
    }

    @Test
    void testNextState() throws IOException, URISyntaxException, FontFormatException, IllegalAccessException, NoSuchFieldException {
        MusicManager mockMusicManager = mock(MusicManager.class);

        HowToPlayMenuViewer mockViewer = mock(HowToPlayMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(2);

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        // Inject the mock MusicManager into the controller using reflection
        Field instanceField = MusicManager.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, mockMusicManager);

        controller.nextState();

        // Ensure that playMusicChoice() is called on the mockMusicManager
        verify(mockMusicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);

        // Ensure that changeState() is called on the mockController
        verify(mockController, times(1)).changeState(ApplicationState.MainMenu);
    }

    @Test
    void testGetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.getButtonSelected()).thenReturn(1);

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }

    @Test
    void testSetButtonSelected() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        controller.setButtonSelected(0);

        // Ensure that setButtonSelected() is called on the mockController
        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws IOException, URISyntaxException, FontFormatException {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        assertEquals(3, controller.userInput());

        // Ensure that userInput() is called on the mockController
        verify(mockController, times(1)).userInput();
    }

}
