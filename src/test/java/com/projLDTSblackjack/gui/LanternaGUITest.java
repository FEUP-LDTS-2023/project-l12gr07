package com.projLDTSblackjack.gui;

import static org.mockito.Mockito.*;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalSize;
import com.projLDTS.blackjack.gui.LanternaGUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LanternaGUITest {

    private LanternaGUI lanternaGUI;
    private Screen mockScreen;
    private TextGraphics mockTextGraphics;

    @BeforeEach
    public void setUp() {
        mockScreen = mock(Screen.class);
        mockTextGraphics = mock(TextGraphics.class);

        TerminalSize mockTerminalSize = new TerminalSize(130, 40);

        lanternaGUI = new LanternaGUI(mockScreen, mockTextGraphics, mockTerminalSize); // Pass appropriate width and height
    }

    @Test
    public void testDrawBlackjack() throws IOException {
        lanternaGUI.drawBlackjack();
        verify(mockTextGraphics, times(10)).putString(any(), anyString());
        verify(mockScreen).refresh();
    }

    @Test
    public void testDrawHowToPlay() {
        lanternaGUI.drawHowToPlay();
        verify(mockTextGraphics, times(10)).putString(any(), anyString());
    }

    @Test
    public void testDrawGetUsername() {
        StringBuilder username = new StringBuilder("TestUser");
        lanternaGUI.drawGetUsername(username);
        verify(mockTextGraphics, times(2)).putString(any(), anyString());
    }

    @Test
    public void testDrawBox() {
        int x = 2;
        int y = 3;
        int width = 6;
        int height = 4;
        TextColor borderColor = TextColor.Factory.fromString("#FF0000"); // Example border color

        // Call the method to be tested
        lanternaGUI.drawBox(x, y, width, height, borderColor);
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                verify(mockTextGraphics).setCharacter(x + i, y + j, ' ');
            }
        }

        // Verify that setCharacter is called for top and bottom borders
        for (int i = 0; i < width; i++) {
            verify(mockTextGraphics).setForegroundColor(borderColor);
            verify(mockTextGraphics).setCharacter(x + i, y, '-');
            verify(mockTextGraphics).setCharacter(x + i, y + height - 1, '-');
        }

        // Verify that setCharacter is called for left and right borders
        for (int i = 1; i < height - 1; i++) {
            verify(mockTextGraphics).setForegroundColor(borderColor);
            verify(mockTextGraphics).setCharacter(x, y + i, '|');
            verify(mockTextGraphics).setCharacter(x + width - 1, y + i, '|');
        }

        // Verify that setForegroundColor is called at the end of the method
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#000000"));
    }

    @Test
    public void testRefresh() throws IOException {
        lanternaGUI.refresh();
        verify(mockScreen, times(1)).refresh();
    }

}
