package com.projLDTS.blackjack.gui;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import com.projLDTS.blackjack.model.game.Cards.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserInput {
    private final LanternaGUI gui;
    private static StringBuilder bet = new StringBuilder();
    private static int thisBet;
    static int credit = 1000;
    static boolean betEnded = false;
    private static int gameResult = 0; // 0 for draw, 1 for win, -1 for loss
    private static int betValue = 0;
    public UserInput(LanternaGUI gui_) {gui = gui_; }

    private static StringBuilder username = new StringBuilder();

    public static void setUsername(String username1) {
        username = new StringBuilder(username1);
    }
    public static StringBuilder getUsername() {
        return username;
    }

    public static int getCredit() {return credit; }
    public static void setCredit(int credit) { UserInput.credit = credit; }


    public static void setBet(StringBuilder bet) {
        UserInput.bet = bet;
    }

    public static StringBuilder getBet() {
        return bet;
    }
    public static int getintBet() {
        return thisBet;
    }
    public static boolean getbetEnded() {
        return betEnded;
    }

    public int MainMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
            System.exit(0);
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        else if (key.getKeyType() == KeyType.ArrowUp) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.ArrowDown) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 0;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            return 3;
        }
        return buttonSelected;
    }

    public int StartMenuInput(int buttonSelected, StringBuilder username) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Backspace && !username.isEmpty()) {
            username.deleteCharAt(username.length() - 1);
        } else if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            if (character == ' ') {
                return buttonSelected; // Space bar pressed (simulate button click)
            } else if (Character.isLetter(character) && username.length() < 20) {
                username.append(character);
            }
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 0;
            if(UserInput.getUsername().toString().isEmpty()){
                if (buttonSelected == 1) buttonSelected = 2;
            }
            return buttonSelected;
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            if(UserInput.getUsername().toString().isEmpty()){
                if (buttonSelected == 1) buttonSelected = 0;
            }
            return buttonSelected;
        } else if (key.getKeyType() == KeyType.Enter) {
            return 3;
        }
        return buttonSelected;
    }

    public int ExitAndHowToPlayMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
            System.exit(0);
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        if (key.getKeyType() == KeyType.ArrowRight) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            return 1;
        }
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            return 0;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            return 2;
        }
        return buttonSelected;
    }
    public int DecksMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
            System.exit(0);
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 3;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.ArrowRight) {
            MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
            buttonSelected++;
            if (buttonSelected == 4) buttonSelected = 0;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            return 4;
        }
        return buttonSelected;
    }
    public int Last10GamesMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
            System.exit(0);
        else if (key.getKeyType() == KeyType.Enter) {
            return 0;
        }
        return -1;
    }

    public int GameInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Backspace && !bet.isEmpty() && !betEnded) {
            UserInput.setCredit(UserInput.getCredit()+Integer.parseInt(UserInput.getBet().toString()));
            bet.deleteCharAt(bet.length() - 1);
            if(!bet.toString().isEmpty()){
                UserInput.setCredit(UserInput.getCredit()-Integer.parseInt(UserInput.getBet().toString()));
            }
        } else if (key.getKeyType() == KeyType.Character && !betEnded) {
            char character = key.getCharacter();
            if (character == ' ') {
                return buttonSelected; // Space bar pressed (simulate button click)
            } else if (Character.isDigit(character) && bet.length() < 6) {
                if (bet.toString().isEmpty() && UserInput.getCredit() > 0){
                    bet.append(character);
                    UserInput.setCredit(UserInput.getCredit()-Integer.parseInt(UserInput.getBet().toString()));
                } else if(Integer.parseInt(UserInput.getBet().toString())*10 + Character.getNumericValue(character) <= UserInput.getCredit()+ Integer.parseInt(UserInput.getBet().toString())){
                    UserInput.setCredit(UserInput.getCredit()+Integer.parseInt(UserInput.getBet().toString()));
                    bet.append(character);
                    UserInput.setCredit(UserInput.getCredit()-Integer.parseInt(UserInput.getBet().toString()));
                }
            }
        }
        thisBet = Integer.parseInt(String.valueOf(bet));
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
            System.exit(0);
        else if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'e' || key.getCharacter() == 'E'))
            return 5; //Return to main menu
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        else if (!UserInput.getBet().toString().isEmpty() && betEnded) {
            if (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown) {
                switch (buttonSelected) {
                    case 0:
                        MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
                        buttonSelected = 2;
                        break;
                    case 2:
                        MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
                        buttonSelected = 0;
                        break;
                }
            }
            else if (key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight) {
                switch (buttonSelected) {
                    case 0:
                        MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
                        buttonSelected = 1;
                        break;
                    case 1:
                        MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
                        buttonSelected = 0;
                        break;
                    case 2:
                        MusicManager.getInstance().playMusicChoice(MusicOptions.OPTION_SELECT);
                        buttonSelected = 1;
                        break;
                }
            }
            else if (key.getKeyType() == KeyType.Enter) {
                return 4;
            }
        }
        if (key.getKeyType() == KeyType.Enter && !UserInput.getBet().toString().isEmpty()){
            betEnded = true;
            MusicManager.getInstance().playMusicChoice(MusicOptions.SHUFFLE);
        }
        return buttonSelected;
    }

    public int playInput() throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        betEnded = false;
        //adicionar bet ao last10games
        setBet(new StringBuilder()); //limpar a bet
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
            System.exit(0);
        else if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'y' || key.getCharacter() == 'Y'))
            return 0;
        else if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'n' || key.getCharacter() == 'N'))
            return 1;
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        return -1;
    }


    public static void setGameResult(int result) {
        gameResult = result;
    }

    public static int getGameResult() {
        return gameResult;
    }

    public static void setBetValue(int value) {
        betValue = value;
    }

    public static int getBetValue() {
        return betValue;
    }
    public static void setBetEnded(boolean ended) {
        betEnded = ended;
    }
}
