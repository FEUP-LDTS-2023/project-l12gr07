package com.projLDTSblackjack.gui;

import com.projLDTS.blackjack.gui.LanternaGUI;
import com.projLDTS.blackjack.gui.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {

    @Mock
    private LanternaGUI gui;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetUsername() {
        UserInput.setUsername("testUsername");
        assertEquals("testUsername", UserInput.getUsername().toString());
    }
    @Test
    void testSetAndGetCredit() {
        UserInput.setCredit(20);
        assertEquals(20, UserInput.getCredit());
    }
    @Test
    void testSetAndGetBet() {
        UserInput.setBet(new StringBuilder("20"));
        assertEquals("20", UserInput.getBet().toString());
    }
    @Test
    void testGetIntBet() {
        UserInput.setintBet(20);
        assertEquals(20, UserInput.getintBet());
    }
    @Test
    void testSetAndGetBetEndedtrue() {
        UserInput.setBetEnded(true);
        assertTrue(UserInput.getbetEnded());
    }
    @Test
    void testSetAndGetBetEndedfalse() {
        assertFalse(UserInput.getbetEnded());
    }
    @Test
    void testSetAndGetGameResult() {
        UserInput.setGameResult(1);
        assertEquals(1, UserInput.getGameResult());
    }
    @Test
    void testSetAndGetBetValue() {
        UserInput.setBetValue(30);
        assertEquals(30, UserInput.getBetValue());
    }

    //por alto deve ser parecido, mas é capaz de ter erros
    /*@Test
    void mainMenuInput() throws IOException {
        UserInput userinput = new UserInput(gui);
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);

        when(gui.getScreen().readInput()).thenReturn(keyStroke);

        int result = userinput.MainMenuInput(1);
        assertEquals(0, result);
        verify(MusicManager.getInstance(), times(1)).playMusicChoice(MusicOptions.OPTION_SELECT);
    }


    @Test
    void playInput() throws IOException {
        UserInput userinput = new UserInput(gui);
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('y');

        when(gui.getScreen().readInput()).thenReturn(keyStroke);

        int result = userinput.playInput();
        assertEquals(0, result);
    }*/

}
