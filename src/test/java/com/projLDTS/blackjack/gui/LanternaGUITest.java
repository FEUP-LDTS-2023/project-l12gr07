//package com.projLDTS.blackjack.gui;
//
//import com.googlecode.lanterna.TerminalPosition;
//import com.googlecode.lanterna.TerminalSize;
//import com.googlecode.lanterna.TextColor;
//import com.googlecode.lanterna.graphics.TextGraphics;
//import com.googlecode.lanterna.screen.Screen;
//import com.projLDTS.blackjack.gui.LanternaGUI;
//import com.projLDTS.blackjack.gui.UserInput;
//import com.projLDTS.blackjack.model.game.Cards.Card;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import java.awt.*;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//public class LanternaGUITest {
//
//    private Screen mockScreen;
//
//    private TextGraphics mockTextGraphics;
//
//    private LanternaGUI lanternaGUI;
//
//    @BeforeEach
//    public void setUp() throws IOException, URISyntaxException, FontFormatException {
//        lanternaGUI = new LanternaGUI(130, 40);
//        mockScreen = Mockito.mock(Screen.class);
//        mockTextGraphics = Mockito.mock(TextGraphics.class);
//        lanternaGUI.setScreen(mockScreen);
//        lanternaGUI.setTextGraphics(mockTextGraphics);
//    }
//
////    @Test
////    public void drawExitTest() {
////        lanternaGUI.drawExit();
////
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).putString(new TerminalPosition(85, 2), "Return to Main Menu: E");
////    }
////
////    @Test
////    public void drawPlayerLostTest() {
////        lanternaGUI.drawPlayerLost();
////
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(2, 15), new TerminalSize(126, 10), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(65, 18), "YOU LOST");
////        verify(mockTextGraphics).putString(new TerminalPosition(50, 20), "DO YOU WANT TO KEEP PLAYING? Y/N");
////    }
////
////    @Test
////    public void drawPlayerWonTest() {
////        lanternaGUI.drawPlayerWon();
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(2, 15), new TerminalSize(126, 10), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(65, 18), "YOU WON");
////        verify(mockTextGraphics).putString(new TerminalPosition(50, 20), "DO YOU WANT TO KEEP PLAYING? Y/N");
////    }
////
////    @Test
////    public void drawPlayDrawTest() {
////        lanternaGUI.drawPlayDraw();
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(2, 15), new TerminalSize(126, 10), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(65, 18), "DRAW");
////        verify(mockTextGraphics).putString(new TerminalPosition(50, 20), "DO YOU WANT TO KEEP PLAYING? Y/N");
////    }
////
////    @Test
////    public void drawPlayerNoCreditTest() {
////        lanternaGUI.drawPlayerNoCredit();
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(2, 15), new TerminalSize(126, 10), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(50, 18), "YOU LOST. THERE IS NO CREDIT LEFT");
////        verify(mockTextGraphics).putString(new TerminalPosition(50, 20), "DO YOU WANT TO PLAY AGAIN? Y/N");
////    }
////
//    @Test
//    public void drawHeartsCardTest() {
//        Card card = new Card("Hearts", "10");
//        int position = 0;
//        int row = 0;
//        lanternaGUI.drawCard(card, position, row);
//
//        ArrayList<String> playingCard = card.getPlayingCard();
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics, times(playingCard.size())).putString(any(TerminalPosition.class), anyString());
//    }
////
////    @Test
////    public void drawDiamondsCardTest() {
////        Card card = new Card("Diamonds", "5");
////        int position = 0;
////        int row = 0;
////        lanternaGUI.drawCard(card, position, row);
////
////        ArrayList<String> playingCard = card.getPlayingCard();
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics, times(playingCard.size())).putString(any(TerminalPosition.class), anyString());
////    }
////    @Test
////    public void drawSpadesCardTest() {
////        Card card = new Card("Spades", "1");
////        int position = 0;
////        int row = 0;
////        lanternaGUI.drawCard(card, position, row);
////
////        ArrayList<String> playingCard = card.getPlayingCard();
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics, times(playingCard.size())).putString(any(TerminalPosition.class), anyString());
////    }
////
////    @Test
////    public void drawClubsCardTest() {
////        Card card = new Card("Clubs", "A");
////        int position = 0;
////        int row = 0;
////        lanternaGUI.drawCard(card, position, row);
////
////        ArrayList<String> playingCard = card.getPlayingCard();
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics, times(playingCard.size())).putString(any(TerminalPosition.class), anyString());
////    }
////
////    @Test
////    public void drawDealerPlayerTextTest() {
////        lanternaGUI.drawDealerPlayerText();
////
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
////
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 8), "D");
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 10), "E");
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 12), "A");
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 14), "L");
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 16), "E");
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 18), "R");
////
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 20), "P");
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 22), "L");
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 24), "A");
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 26), "Y");
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 28), "E");
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 30), "R");
////    }
////
////    @Test
////    public void drawValuesDealerTest() {
////        int v = 20;
////        lanternaGUI.drawValues(v, true);
////
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).putString(new TerminalPosition(90, 14), String.valueOf(v));
////    }
////
////    @Test
////    public void drawValuesPlayerTest() {
////        int v = 15;
////        lanternaGUI.drawValues(v, false);
////
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).putString(new TerminalPosition(90, 25), String.valueOf(v));
////    }
//
//    @Test
//    public void drawLast10LinesTest() {
//        List<String> last10lines = Arrays.asList("+ Positive value", "- Negative value");
//        lanternaGUI.drawLast10Lines(last10lines);
//
//        int row = 4;
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
//        verify(mockTextGraphics).putString(new TerminalPosition(82, row), "+ Positive value");
//        row += 3;
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
//        verify(mockTextGraphics).putString(new TerminalPosition(82, row), "- Negative value");
//    }
//
////    @Test
////    public void drawBetTest() {
////        lanternaGUI.drawBet();
////
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).putString(new TerminalPosition(62, 32), "bet");
////        verify(mockTextGraphics).putString(new TerminalPosition(61, 34), UserInput.getBet().toString());
////    }
////
////    @Test
////    public void testDrawBlackjack() throws IOException {
////        lanternaGUI.drawBlackjack();
////
////        verify(mockTextGraphics, times(10)).putString(any(TerminalPosition.class), anyString());
////        verify(mockScreen, times(1)).refresh();
////    }
////
////    @Test
////    public void testDrawHowToPlay() throws IOException {
////        lanternaGUI.drawHowToPlay();
////
////        verify(mockTextGraphics, times(10)).putString(any(TerminalPosition.class), anyString());
////    }
////
////    @Test
////    public void drawOneButtonNotSelectedTest() {
////        lanternaGUI.drawOneButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(29, 27), new TerminalSize(20, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 28), "One Deck");
////    }
//
//    @Test
//    public void testGetUsername() {
//        StringBuilder username = new StringBuilder("TestUsername");
//        lanternaGUI.drawGetUsername(username);
//
//        verify(mockTextGraphics).putString(new TerminalPosition(46, 17), "Insert your username: (Max 20 characters)\n");
//
//        int maxUsernameLength = 20;
//        String truncatedUsername = username.toString();
//        if (truncatedUsername.length() > maxUsernameLength) {
//            truncatedUsername = truncatedUsername.substring(0, maxUsernameLength);
//        }
//        int boxWidth = 22;
//        int boxHeight = 3;
//        int x = 48 + 5 + 2;
//        int y = 19;
//        int usernameX = x + (boxWidth - truncatedUsername.length()) / 2;
//        int usernameY = y + boxHeight / 2;
//        verify(mockTextGraphics).putString(new TerminalPosition(usernameX, usernameY), truncatedUsername);
//    }
//
//    @Test
//    public void drawHowToPlayPage1Test() {
//        lanternaGUI.drawHowToPlayPage1();
//
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 18), "1. **Objective:**\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 20), "   The goal of Blackjack is to beat the dealer by having a hand value");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 22), "2. **Card Values:**\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 24), "   - Number cards (2-10) are worth their face value.\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 25), "   - Face cards (Jack, Queen, King) are each worth 10.");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 26), "   - Aces can be worth 1 or 11, depending on which value benefits the hand more.\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(63, 30), "1/4");
//    }
//
//    @Test
//    public void drawHowToPlayPage2Test() {
//        lanternaGUI.drawHowToPlayPage2();
//
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 18), "3. **Game Flow:**\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 20), "   - Each player, including the dealer, is dealt two cards.\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 21), "   - Players can 'Hit' to receive additional cards or 'Stand' to keep their current hand.\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 22), "   - If the total value of a player's cards exceeds 21, they bust and lose the round.\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 24), "4. **Winning:**\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 26), "   - If a player's total is closer to 21 than the dealer's, they win.\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 27), "   - If the dealer busts and the player doesn't, the player wins.\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(9, 28), "   - If both the player and dealer have the same total, it's a draw (no one wins or loses).\n");
//        verify(mockTextGraphics).putString(new TerminalPosition(63, 30), "2/4");
//    }
////
////    @Test
////    public void drawHowToPlayPage3Test() {
////        lanternaGUI.drawHowToPlayPage3();
////
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 18), "5. **Special Moves:**\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 20), "   - **Double Down:** Double your original bet and receive only one additional card.\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 22), "6. **Blackjack:**\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 24), "   - A \"Blackjack\" is an Ace and a 10-value card.\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(63, 30), "3/4");
////    }
////
////    @Test
////    public void drawHowToPlayPage4Test() {
////        lanternaGUI.drawHowToPlayPage4();
////
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 18), "7. **Tips:**\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 20), "   - Pay attention to the dealer's upcard and adjust your strategy accordingly.\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 21), "   - Practice basic strategy to maximize your chances of winning.\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(9, 23), "8. **Have Fun and Good Luck!**\n");
////        verify(mockTextGraphics).putString(new TerminalPosition(63, 30), "4/4");
////    }
////
////    @Test
////    public void clearTest() throws IOException {
////        lanternaGUI.clear();
////
////        verify(mockScreen).clear();
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(130, 40), ' ');
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////    }
////
////    @Test
////    public void closeTest() throws IOException {
////        lanternaGUI.close();
////
////        verify(mockScreen).close();
////    }
////
////    @Test
////    public void refreshTest() throws IOException {
////        lanternaGUI.refresh();
////
////        verify(mockScreen).refresh();
////    }
////
////    @Test
////    public void drawExitQTest() throws IOException {
////        lanternaGUI.drawExitQ();
////
////        verify(mockTextGraphics).putString(new TerminalPosition(52, 22), "Are you sure you want to");
////        verify(mockTextGraphics).putString(new TerminalPosition(58, 23), "leave the game?");
////    }
////
////    @Test
////    public void drawDecksTextTest() throws IOException {
////        lanternaGUI.drawDecksText();
////
////        verify(mockTextGraphics).putString(new TerminalPosition(52, 22), "How many decks of cards?");
////    }
////
////    @Test
////    public void drawLast10GamesTextTest() {
////        lanternaGUI.drawLast10GamesText();
////
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 3), "88                                        ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 4), "88                                 ,d     ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 5), "88                                 ,d     ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 6), "88          ,adPPYYba, ,adPPYba, MM88MMM  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 7), "88          \"\"     `Y8 I8[    \"\"   88    ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 8), "88          ,adPPPPP88  `\"Y8ba,    88   ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 9), "88          88,    ,88 aa    ]8I   88,    ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 10), "88888888888 `\"8bbdP\"Y8 `\"YbbdP\"'   \"Y888  ");
////
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 13), "    88    ,a8888a,     ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 14), "  ,d88  ,8P\"'  `\"Y8,   ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 15), "888888 ,8P        Y8,  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 16), "    88 88          88  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 17), "    88 88          88  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 18), "    88 `8b        d8'  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 19), "    88  `8ba,  ,ad8'   ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 20), "    88    \"Y8888P\"     ");
////
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 23), "  ,ad8888ba,                                                      ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 24), " d8\"'    `\"8b                                                     ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 25), "d8'                                                               ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 26), "88            ,adPPYYba, 88,dPYba,,adPYba,   ,adPPYba, ,adPPYba,  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 27), "88      88888 \"\"     `Y8 88P'   \"88\"    \"8a a8P     88 I8[    \"\"  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 28), "Y8,        88 ,adPPPPP88 88      88      88 8PP\"\"\"\"\"\"\"  `\"Y8ba,   ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 29), " Y8a.    .a88 88,    ,88 88      88      88 \"8b,   ,aa aa    ]8I  ");
////        verify(mockTextGraphics).putString(new TerminalPosition(7, 30), "  `\"Y88888P\"  `\"8bbdP\"Y8 88      88      88  `\"Ybbd8\"' `\"YbbdP\"'  ");
////    }
////
////    @Test
////    public void drawCreditTest() {
////        lanternaGUI.drawCredit();
////
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
////        verify(mockTextGraphics).putString(new TerminalPosition(61, 5), "credit");
////        int a = UserInput.getCredit();
////        verify(mockTextGraphics).putString(new TerminalPosition(62, 6), String.valueOf(a));
////    }
////
////    @Test
////    public void drawLineTest() {
////        lanternaGUI.drawLine();
////
////        verify(mockTextGraphics).putString(new TerminalPosition(40, 20), "--------------------------------------------------");
////    }
////
////    @Test
////    public void drawOneButtonSelectedTest() {
////        lanternaGUI.drawOneButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(29, 27), new TerminalSize(20, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 28), "One Deck");
////    }
////
////    @Test
////    public void drawTwoButtonSelectedTest() {
////        lanternaGUI.drawTwoButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(53, 27), new TerminalSize(21, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(59, 28), "Two Decks");
////    }
////
////    @Test
////    public void drawTwoButtonNotSelectedTest() {
////        lanternaGUI.drawTwoButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(53, 27), new TerminalSize(21, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(59, 28), "Two Decks");
////    }
////
////    @Test
////    public void drawInfButtonSelectedTest() {
////        lanternaGUI.drawInfButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(79, 27), new TerminalSize(21, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(85, 28), "Infinite");
////    }
////
////    @Test
////    public void drawInfButtonNotSelectedTest() {
////        lanternaGUI.drawInfButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(79, 27), new TerminalSize(21, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(85, 28), "Infinite");
////    }
//
//    @Test
//    public void drawRetDecksButtonSelectedTest() {
//        lanternaGUI.drawRetDecks(true);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(57, 34), new TerminalSize(14, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(61, 35), "RETURN");
//    }
//
//    @Test
//    public void drawRetDecksButtonNotSelectedTest() {
//        lanternaGUI.drawRetDecks(false);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(57, 34), new TerminalSize(14, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(61, 35), "RETURN");
//    }
//
//    @Test
//    public void drawHitButtonSelectedTest() {
//        lanternaGUI.drawHitButton(true);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(43, 31), new TerminalSize(14, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(48, 32), "HIT");
//    }
//
//    @Test
//    public void drawHitButtonNotSelectedTest() {
//        lanternaGUI.drawHitButton(false);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(43, 31), new TerminalSize(14, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(48, 32), "HIT");
//    }
//
//    @Test
//    public void drawStandButtonSelectedTest() {
//        lanternaGUI.drawStandButton(true);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(70, 31), new TerminalSize(14, 7), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(74, 34), "STAND");
//    }
//
//    @Test
//    public void drawStandButtonNotSelectedTest() {
//        lanternaGUI.drawStandButton(false);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(70, 31), new TerminalSize(14, 7), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(74, 34), "STAND");
//    }
//
//    @Test
//    public void drawDoubleDownButtonNotSelectedTest() {
//        lanternaGUI.drawDoubleDownButton(false);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(43, 35), new TerminalSize(14, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(44, 36), "DOUBLE DOWN");
//    }
//
//    @Test
//    public void drawDoubleDownButtonSelectedTest() {
//        lanternaGUI.drawDoubleDownButton(true);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(43, 35), new TerminalSize(14, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(44, 36), "DOUBLE DOWN");
//    }
//
//    @Test
//    public void drawExitButtonSelectedTest() {
//        lanternaGUI.drawExitButton(true);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(50, 29), new TerminalSize(30, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(63, 30), "EXIT");
//    }
//
//    @Test
//    public void drawExitButtonNotSelectedTest() {
//        lanternaGUI.drawExitButton(false);
//
//        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
//        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
//        verify(mockTextGraphics).fillRectangle(new TerminalPosition(50, 29), new TerminalSize(30, 3), ' ');
//        verify(mockTextGraphics).putString(new TerminalPosition(63, 30), "EXIT");
//    }
//
////    @Test
////    public void drawStartButtonSelectedTest() {
////        lanternaGUI.drawStartButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(50, 21),new TerminalSize(30, 3),' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(63, 22), "START");
////    }
////
////    @Test
////    public void drawStartButtonNotSelectedTest() {
////        lanternaGUI.drawStartButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(50, 21),new TerminalSize(30, 3),' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(63, 22), "START");
////    }
////
////    @Test
////    public void drawHowToPlayButtonSelectedTest() {
////        lanternaGUI.drawHowToPlayButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(50, 25), new TerminalSize(30, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(60, 26), "HOW TO PLAY");
////    }
////
////    @Test
////    public void drawHowToPlayButtonNotSelectedTest() {
////        lanternaGUI.drawHowToPlayButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(50, 25), new TerminalSize(30, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(60, 26), "HOW TO PLAY");
////    }
////
////    @Test
////    public void drawLast10GamesButtonSelectedTest() {
////        lanternaGUI.drawLast10GamesButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(112, 35), new TerminalSize(16, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(115, 36), "LAST GAMES");
////    }
////
////    @Test
////    public void drawLast10GamesButtonNotSelectedTest() {
////        lanternaGUI.drawLast10GamesButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(112, 35), new TerminalSize(16, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(115, 36), "LAST GAMES");
////    }
////
////    @Test
////    public void drawbReturnButtonSelectedTest() {
////        lanternaGUI.drawbReturnButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(35, 24), new TerminalSize(14, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(39, 25), "RETURN");
////    }
////
////    @Test
////    public void drawbReturnButtonNotSelectedTest() {
////        lanternaGUI.drawbReturnButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(35, 24), new TerminalSize(14, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(39, 25), "RETURN");
////    }
////
////    @Test
////    public void drawPlayButtonSelectedTest() {
////        lanternaGUI.drawPlayButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(83, 24), new TerminalSize(12, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(87, 25), "PLAY");
////    }
////
////    @Test
////    public void drawPlayButtonNotSelectedTest() {
////        lanternaGUI.drawPlayButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(83, 24), new TerminalSize(12, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(87, 25), "PLAY");
////    }
////
////    @Test
////    public void drawYesButtonSelectedTest() {
////        lanternaGUI.drawYesButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(29, 27), new TerminalSize(15, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 28), "YES");
////    }
////
////    @Test
////    public void drawYesButtonNotSelectedTest() {
////        lanternaGUI.drawYesButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(29, 27), new TerminalSize(15, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(35, 28), "YES");
////    }
////
////    @Test
////    public void drawNoButtonSelectedTest() {
////        lanternaGUI.drawNoButton(true);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(85, 27), new TerminalSize(15, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(91, 28), "NO");
////    }
////
////    @Test
////    public void drawNoButtonNotSelectedTest() {
////        lanternaGUI.drawNoButton(false);
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(85, 27), new TerminalSize(15, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(91, 28), "NO");
////    }
////
////    @Test
////    public void drawNextTest() {
////        lanternaGUI.drawNext();
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(70, 33), new TerminalSize(50, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 34), "-->");
////    }
////
////    @Test
////    public void drawPreviousTest() {
////        lanternaGUI.drawPrevious();
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(9, 33), new TerminalSize(50, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(30, 34), "<--");
////    }
////
////    @Test
////    public void drawRet2Test() {
////        lanternaGUI.drawRet2();
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(70, 33), new TerminalSize(50, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(94, 34), "RETURN");
////    }
////
////    @Test
////    public void drawRet1Test() {
////        lanternaGUI.drawRet1();
////
////        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
////        verify(mockTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#727272"));
////        verify(mockTextGraphics).fillRectangle(new TerminalPosition(9, 33), new TerminalSize(50, 3), ' ');
////        verify(mockTextGraphics).putString(new TerminalPosition(30, 34), "RETURN");
////    }
//}
