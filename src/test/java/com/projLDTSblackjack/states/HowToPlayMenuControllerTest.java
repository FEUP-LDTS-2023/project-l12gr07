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

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HowToPlayMenuControllerTest {
    @Mock
    private HowToPlayMenuViewer mockHowToPlayMenuViewer;
    @Mock
    private ApplicationStateController mockApplicationStateController;
    @InjectMocks
    private HowToPlayMenuController howToPlayMenuController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        howToPlayMenuController = new HowToPlayMenuController(mockApplicationStateController);
        when(mockApplicationStateController.getStateViewer()).thenReturn(mockHowToPlayMenuViewer);
    }

    @Test
    public void runShouldChangeStateWhenInputIs1AndPageIs3() throws Exception {
        HowToPlayMenuViewer mockHowToPlayMenuViewer = mock(HowToPlayMenuViewer.class);
        when(mockHowToPlayMenuViewer.getPage()).thenReturn(3);
        when(mockApplicationStateController.getStateViewer()).thenReturn(mockHowToPlayMenuViewer);
        when(mockApplicationStateController.userInput()).thenReturn(1);
        howToPlayMenuController.run();
        verify(mockApplicationStateController, times(1)).changeState(eq(ApplicationState.MainMenu));
    }
    @Test
    public void runShouldChangeStateWhenInputIs0AndPageIs0() throws Exception {
        HowToPlayMenuViewer mockHowToPlayMenuViewer = mock(HowToPlayMenuViewer.class);
        when(mockHowToPlayMenuViewer.getPage()).thenReturn(0);
        when(mockApplicationStateController.getStateViewer()).thenReturn(mockHowToPlayMenuViewer);
        when(mockApplicationStateController.userInput()).thenReturn(0);
        howToPlayMenuController.run();
        verify(mockApplicationStateController, times(1)).changeState(any(ApplicationState.class));
    }
    /*
    @Test
    void runShouldUpdatePageWhenInputIsNotMinusOne() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(2); // Replace with your desired input

        howToPlayMenuController.run();

        verify(mockApplicationStateController, times(1)).setButtonSelected(2);
        verify(mockApplicationStateController, times(1)).redraw();
        verify(mockHowToPlayMenuViewer, times(1)).getPage();
        verify(mockHowToPlayMenuViewer, times(1)).setPage(anyInt());
    }
     */

    @Test
    void runShouldNotUpdatePageWhenInputIsMinusOne() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(-1);

        howToPlayMenuController.run();

        verify(mockApplicationStateController, never()).setButtonSelected(anyInt());
        verify(mockApplicationStateController, never()).redraw();
        verify(mockHowToPlayMenuViewer, never()).getPage();
        verify(mockHowToPlayMenuViewer, never()).setPage(anyInt());
    }

    /*
    @Test
    public void runShouldSetButtonSelectedAndChangePageWhenInputIsNotMinusOne() throws Exception {
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
    public void runShouldBreakLoopWhenInputIsMinusOne() throws Exception {
        when(mockApplicationStateController.userInput()).thenReturn(-1);

        howToPlayMenuController.run();

        verify(mockApplicationStateController, times(0)).setButtonSelected(anyInt());
        verify(mockApplicationStateController, times(0)).redraw();
        verify(howToPlayMenuController, times(0)).getPage();
        verify(mockApplicationStateController, times(0)).getButtonSelected();
        verify(howToPlayMenuController, times(0)).setPage(anyInt());
    }*/

    @Test
    void testGetPage() throws Exception {
        HowToPlayMenuViewer mockViewer = mock(HowToPlayMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        when(mockViewer.getPage()).thenReturn(2);
        assertEquals(2, controller.getPage());

        verify(mockController, times(1)).getStateViewer();
    }

    @Test
    void testSetPage() throws Exception {
        HowToPlayMenuViewer mockViewer = mock(HowToPlayMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        controller.setPage(3);

        verify(mockViewer, times(1)).setPage(3);
    }

    @Test
    void testNextState() throws Exception {
        MusicManager mockMusicManager = mock(MusicManager.class);

        HowToPlayMenuViewer mockViewer = mock(HowToPlayMenuViewer.class);
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        when(mockController.getStateViewer()).thenReturn(mockViewer);
        when(mockController.getButtonSelected()).thenReturn(2);

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

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

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        assertEquals(1, controller.getButtonSelected());

        verify(mockController, times(1)).getButtonSelected();
    }

    @Test
    void testSetButtonSelected() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        controller.setButtonSelected(0);

        verify(mockController, times(1)).setButtonSelected(0);
    }

    @Test
    void testUserInput() throws Exception {
        ApplicationStateController mockController = mock(ApplicationStateController.class);
        when(mockController.userInput()).thenReturn(3);

        HowToPlayMenuController controller = new HowToPlayMenuController(mockController);

        assertEquals(3, controller.userInput());

        verify(mockController, times(1)).userInput();
    }

}
