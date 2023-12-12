package com.projLDTS.blackjack.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI {
    private final Screen screen;
    private TerminalSize size;
    private TextGraphics textGraphics;
    TextColor buttonColor = TextColor.Factory.fromString("#00CA4C");
    TextColor selectedColor = TextColor.Factory.fromString("#FF0000");

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        size = new TerminalSize(width, height);
//        URL resource = getClass().getClassLoader().getResource("GamePlayed.ttf");
//        File fontFile = new File(resource.toURI());
//        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
//
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        ge.registerFont(font);

        DefaultTerminalFactory factory = new DefaultTerminalFactory();

//        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
//        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
//        factory.setTerminalEmulatorFontConfiguration(fontConfig);
//        factory.setForceAWTOverSwing(true);

        Terminal terminal = factory.setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        textGraphics = newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), size, ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        screen.refresh();
    }

    public Screen getScreen() {
        return this.screen;
    }

    public void drawBlackjack() throws IOException {
        textGraphics.putString(new TerminalPosition(24, 5), "BB          BB                       BB        BB                       BB         \n");
        textGraphics.putString(new TerminalPosition(24, 6), "BB          BB                       BB        \"\"                       BB         \n");
        textGraphics.putString(new TerminalPosition(24, 7), "BB          BB                       BB                                 BB         \n");
        textGraphics.putString(new TerminalPosition(24, 8), "BB,dPPYba,  BB ,adPPYYba,  ,adPPYba, BB   ,dB  BB ,adPPYYba,  ,adPPYba, BB   ,dB   \n");
        textGraphics.putString(new TerminalPosition(24, 9), "BBP'    \"Ba BB \"\"     `YB aB\"     \"\" BB ,aB'   BB \"\"     `YB aB\"     \"\" BB ,aB\"    \n");
        textGraphics.putString(new TerminalPosition(24, 10), "BB       dB BB ,adPPPPPBB Bb         BBBB[     BB ,adPPPPPBB Bb         BBBB[      \n");
        textGraphics.putString(new TerminalPosition(24, 11), "BBb,   ,aB\" BB BB,    ,BB \"Ba,   ,aa BB`\"Yba,  BB BB,    ,BB \"Ba,   ,aa BB`\"Yba,   \n");
        textGraphics.putString(new TerminalPosition(24, 12), "BY\"YbbdB\"'  BB `\"BbbdP\"YB  `\"YbbdB\"' BB   `YBa BB `\"BbbdP\"YB  `\"YbbdB\"' BB   `YBa  \n");
        textGraphics.putString(new TerminalPosition(24, 13), "                                              ,BB                                  \n");
        textGraphics.putString(new TerminalPosition(24, 14), "                                            BBBP\"\n");
        screen.refresh();
    }

    public void drawHowToPlay() {
        textGraphics.putString(new TerminalPosition(9, 5), "BB        BB                                   BBBBBBBBBBBB              BBBBBBBBba  BB                         \n");
        textGraphics.putString(new TerminalPosition(9, 6), "BB        BB                                        BB                   BB      \"Bb BB                         \n");
        textGraphics.putString(new TerminalPosition(9, 7), "BB        BB                                        BB                   BB      ,BP BB                         \n");
        textGraphics.putString(new TerminalPosition(9, 8), "BBaaaaaaaaBB  ,adPPYba,  Bb      db      dB         BB  ,adPPYba,        BBaaaaaaBP' BB ,adPPYYba, Bb       dB  \n");
        textGraphics.putString(new TerminalPosition(9, 9), "BB\"\"\"\"\"\"\"\"BB aB\"     \"Ba `Bb    dBBb    dB'         BB aB\"     \"Ba       BB\"\"\"\"\"\"'   BB \"\"     `YB `Bb     dB'  \n");
        textGraphics.putString(new TerminalPosition(9, 10), "BB        BB Bb       dB  `Bb  dB'`Bb  dB'          BB Bb       dB       BB          BB ,adPPPPPBB  `Bb   dB'   \n");
        textGraphics.putString(new TerminalPosition(9, 11), "BB        BB \"Ba,   ,aB\"   `BbdB'  `BbdB'           BB \"Ba,   ,aB\"       BB          BB BB,    ,BB   `Bb,dB'    \n");
        textGraphics.putString(new TerminalPosition(9, 12), "BB        BB  `\"YbbdP\"'      YP      YP             BB  `\"YbbdP\"'        BB          BB `\"BbbdP\"YB     YBB'     \n");
        textGraphics.putString(new TerminalPosition(9, 13), "                                                                                                       dB'      \n");
        textGraphics.putString(new TerminalPosition(9, 14), "                                                                                                      dB'       ");
    }

    public void drawGetUsername(StringBuilder username) {
        textGraphics.putString(new TerminalPosition(46, 17), "Insert your username: (Max 20 characters)\n");

        int maxUsernameLength = 20;

        int boxWidth = 22;
        int boxHeight = 3;
        int x = 48 + 5 + 2;
        int y = 19;

        String truncatedUsername = username.toString();
        if (truncatedUsername.length() > maxUsernameLength) {
            truncatedUsername = truncatedUsername.substring(0, maxUsernameLength);
        }
        int usernameX = x + (boxWidth - truncatedUsername.length()) / 2;
        int usernameY = y + boxHeight / 2;
        textGraphics.putString(new TerminalPosition(usernameX, usernameY), truncatedUsername);

        drawBox(x, y, boxWidth, boxHeight, TextColor.Factory.fromString("#028A02"));
    }

    private void drawBox(int x, int y, int width, int height, TextColor borderColor) {
        for (int i = 0; i < width; i++) {
            textGraphics.setForegroundColor(borderColor);
            textGraphics.setCharacter(x + i, y, '-');
            textGraphics.setCharacter(x + i, y + height - 1, '-');
        }
        for (int i = 1; i < height - 1; i++) {
            textGraphics.setForegroundColor(borderColor);
            textGraphics.setCharacter(x, y + i, '|');
            textGraphics.setCharacter(x + width - 1, y + i, '|');
        }
    }

    public void drawExitButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(50, 29),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(63, 30), "EXIT");
    }

    public void drawStartButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);        textGraphics.fillRectangle(new TerminalPosition(50, 21),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(63, 22), "START");
    }

    public void drawHowToPlayButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(50, 25),new TerminalSize(30, 3),' ');
        textGraphics.putString(new TerminalPosition(60, 26), "HOW TO PLAY");
    }

    public void drawLast10GamesButton(boolean selected) throws IOException {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(112, 35), new TerminalSize(16, 3),' ');
        textGraphics.putString(new TerminalPosition(115, 36), "LAST GAMES");
    }

    public void drawbReturnButton(boolean selected) throws IOException {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(35, 24), new TerminalSize(14, 3),' ');
        textGraphics.putString(new TerminalPosition(39, 25), "RETURN");
    }

    public void drawPlayButton(boolean selected) throws IOException {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(83, 24), new TerminalSize(12, 3),' ');
        textGraphics.putString(new TerminalPosition(87, 25), "PLAY");
    }

    public void drawYesButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(29, 27), new TerminalSize(15, 3),' ');
        textGraphics.putString(new TerminalPosition(35, 28), "YES");
    }

    public void drawNoButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(85, 27), new TerminalSize(15, 3),' ');
        textGraphics.putString(new TerminalPosition(91, 28), "NO");
    }

    public void drawRet1() {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(9, 33), new TerminalSize(50, 3),' ');
        textGraphics.putString(new TerminalPosition(30, 34), "RETURN");
    }

    public void drawNext() {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(70, 33), new TerminalSize(50, 3),' ');
        textGraphics.putString(new TerminalPosition(94, 34), "-->");
    }

    public void drawPrevious() {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(9, 33), new TerminalSize(50, 3),' ');
        textGraphics.putString(new TerminalPosition(30, 34), "<--");
    }

    public void drawRet2() {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(70, 33), new TerminalSize(50, 3),' ');
        textGraphics.putString(new TerminalPosition(94, 34), "RETURN");
    }

    public void drawHowToPlayPage1() {
        drawHowToPlay();
        textGraphics.putString(new TerminalPosition(9, 18), "1. **Objective:**\n");
        textGraphics.putString(new TerminalPosition(9, 19), "   The goal of Blackjack is to beat the dealer by having a hand value");
        textGraphics.putString(new TerminalPosition(9, 21), "2. **Card Values:**\n");
        textGraphics.putString(new TerminalPosition(9, 22), "   - Number cards (2-10) are worth their face value.\n");
        textGraphics.putString(new TerminalPosition(9, 23), "   - Face cards (Jack, Queen, King) are each worth 10.");
        textGraphics.putString(new TerminalPosition(9, 24), "   - Aces can be worth 1 or 11, depending on which value benefits the hand more.\n");
        textGraphics.putString(new TerminalPosition(63, 30), "1/4");
    }

    public void drawHowToPlayPage2() {
        drawHowToPlay();
        textGraphics.putString(new TerminalPosition(9, 18), "3. **Game Flow:**\n");
        textGraphics.putString(new TerminalPosition(9, 19), "   - Each player, including the dealer, is dealt two cards.\n");
        textGraphics.putString(new TerminalPosition(9, 20), "   - Players can 'Hit' to receive additional cards or 'Stand' to keep their current hand.\n");
        textGraphics.putString(new TerminalPosition(9, 21), "   - If the total value of a player's cards exceeds 21, they bust and lose the round.\n");
        textGraphics.putString(new TerminalPosition(9, 24), "4. **Winning:**\n");
        textGraphics.putString(new TerminalPosition(9, 25), "   - If a player's total is closer to 21 than the dealer's, they win.\n");
        textGraphics.putString(new TerminalPosition(9, 26), "   - If the dealer busts and the player doesn't, the player wins.\n");
        textGraphics.putString(new TerminalPosition(9, 27), "   - If both the player and dealer have the same total, it's a push (no one wins or loses).\n");
        textGraphics.putString(new TerminalPosition(63, 30), "2/4");
    }

    public void drawHowToPlayPage3() {
        drawHowToPlay();
        textGraphics.putString(new TerminalPosition(9, 18), "5. **Special Moves:**\n");
        textGraphics.putString(new TerminalPosition(9, 19), "   - **Double Down:** Double your original bet and receive only one additional card.\n");
        textGraphics.putString(new TerminalPosition(9, 20), "   - **Split:** If dealt two cards of the same rank, split them into two separate hands, each with its bet.\n");
        textGraphics.putString(new TerminalPosition(9, 23), "6. **Blackjack:**\n");
        textGraphics.putString(new TerminalPosition(9, 24), "   - A \"Blackjack\" is an Ace and a 10-value card. It usually pays 3:2.\n");
        textGraphics.putString(new TerminalPosition(63, 30), "3/4");
    }

    public void drawHowToPlayPage4() {
        drawHowToPlay();
        textGraphics.putString(new TerminalPosition(9, 17), "7. **Tips:**\n");
        textGraphics.putString(new TerminalPosition(9, 18), "   - Pay attention to the dealer's upcard and adjust your strategy accordingly.\n");
        textGraphics.putString(new TerminalPosition(9, 19), "   - Practice basic strategy to maximize your chances of winning.\n");
        textGraphics.putString(new TerminalPosition(9, 21), "8. **Commands:**\n");
        textGraphics.putString(new TerminalPosition(9, 22), "   - Type 'h' to Hit.\n");
        textGraphics.putString(new TerminalPosition(9, 23), "   - Type 's' to Stand.\n");
        textGraphics.putString(new TerminalPosition(9, 24), "   - Type 'd' to Double Down.\n");
        textGraphics.putString(new TerminalPosition(9, 25), "   - Type 'p' to Split (if eligible).\n");
        textGraphics.putString(new TerminalPosition(9, 27), "9. **Have Fun and Good Luck!**\n");
        textGraphics.putString(new TerminalPosition(63, 30), "4/4");
    }

    public void drawOneButton (boolean selected){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(29, 27), new TerminalSize(20, 3),' ');
        textGraphics.putString(new TerminalPosition(35, 28), "One Deck");
    }
    public void drawTwoButton (boolean selected){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(53, 27), new TerminalSize(21, 3),' ');
        textGraphics.putString(new TerminalPosition(59, 28), "Two Decks");
    }
    public void drawInfButton (boolean selected){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(79, 27), new TerminalSize(21, 3),' ');
        textGraphics.putString(new TerminalPosition(85, 28), "Infinite");
    }

    public void drawRetDecks(boolean selected) throws IOException {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(10, 34), new TerminalSize(14, 3),' ');
        textGraphics.putString(new TerminalPosition(14, 35), "RETURN");
    }

    public void clear() throws IOException {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#03C04A"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), size, ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        refresh();
    }

    public void close() throws IOException {
        screen.close();
    }

    public void refresh() throws IOException {
        screen.refresh();
    }

    public TextGraphics newTextGraphics() {
        return this.screen.newTextGraphics();
    }

    public void drawExitQ() throws IOException {
        drawBlackjack();
        textGraphics.putString(new TerminalPosition(52, 22), "Are you sure you want to");
        textGraphics.putString(new TerminalPosition(58, 23), "leave the game?");
    }

    public void drawDecksText() throws IOException {
        drawBlackjack();
        textGraphics.putString(new TerminalPosition(52, 22), "How many decks of cards?");
    }

    public void drawLast10GamesText() {
        textGraphics.putString(new TerminalPosition(52, 10), "Last 10 Games");
    }

    public void drawCard(String suit, String rank) {
    }

    public void drawHiddenCard() {
    }

    public void drawCredit() {
        textGraphics.putString(new TerminalPosition(61, 5), "credit");
        // change this to correct credit
        textGraphics.putString(new TerminalPosition(61, 8), "900 €");
    }

    public void drawLine() {
        textGraphics.putString(new TerminalPosition(40, 20), "--------------------------------------------------");
    }

    public void drawBet() {
        textGraphics.putString(new TerminalPosition(62, 32), "bet");
        // change this to correct bet
        textGraphics.putString(new TerminalPosition(60, 34), "10 €");

    }

    public void drawHitButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(43, 31), new TerminalSize(14, 3),' ');
        textGraphics.putString(new TerminalPosition(48, 32), "HIT");
    }

    public void drawStandButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(70, 31), new TerminalSize(14, 3),' ');
        textGraphics.putString(new TerminalPosition(75, 32), "STAND");
    }

    public void drawDoubleDownButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(43, 35), new TerminalSize(14, 3),' ');
        textGraphics.putString(new TerminalPosition(44, 36), "DOUBLE DOWN");
    }

    public void drawSplitButton(boolean selected) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (selected) textGraphics.setBackgroundColor(selectedColor);
        else textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(70, 35), new TerminalSize(14, 3),' ');
        textGraphics.putString(new TerminalPosition(75, 36), "SPLIT");
    }

    public void drawExit() {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setBackgroundColor(buttonColor);
        textGraphics.fillRectangle(new TerminalPosition(70, 35), new TerminalSize(61, 3),' ');
        textGraphics.putString(new TerminalPosition(85, 2), "Return to Main Menu: E");
    }
}
