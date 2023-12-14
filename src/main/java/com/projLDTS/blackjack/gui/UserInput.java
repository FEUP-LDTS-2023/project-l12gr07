package com.projLDTS.blackjack.gui;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.projLDTS.blackjack.model.game.Cards.Player;

import java.io.IOException;

public class UserInput {
    private final LanternaGUI gui;
    private static StringBuilder bet = new StringBuilder();
    static int credit = 1000;
    static boolean betEnded = false;
    public UserInput(LanternaGUI gui_) {gui = gui_; }


    public static int getCredit() { return credit; }
    public static void setCredit(int credit) { UserInput.credit = credit; }

    public static void setBet(StringBuilder bet){
        UserInput.bet = bet;
    }
    public static StringBuilder getBet(){
        return bet;
    }
    public static boolean getbetEnded(){
        return betEnded;
    }
    int c = 0;

    public int MainMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            System.exit(0);
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        else if (key.getKeyType() == KeyType.ArrowUp) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.ArrowDown) {
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
        if (key.getKeyType() == KeyType.Backspace && username.length() > 0) {
            username.deleteCharAt(username.length() - 1);
        } else if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            if (character == ' ') {
                return buttonSelected; // Space bar pressed (simulate button click)
            } else if (Character.isLetter(character)) {
                username.append(character);
            }
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            buttonSelected++;
            if (buttonSelected == 3) buttonSelected = 0;
            return buttonSelected;
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 2;
            return buttonSelected;
        } else if (key.getKeyType() == KeyType.Enter) {
            return 3;
        }
        return buttonSelected;
    }

    public int ExitAndHowToPlayMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            System.exit(0);
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        if (key.getKeyType() == KeyType.ArrowRight) {
            return 1;
        }
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            return 0;
        }
        else if (key.getKeyType() == KeyType.Enter) {
            return 2;
        }
        return buttonSelected;
    }
    public int DecksMenuInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            System.exit(0);
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            buttonSelected--;
            if (buttonSelected == -1) buttonSelected = 3;
            return buttonSelected;
        }
        else if (key.getKeyType() == KeyType.ArrowRight) {
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
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            System.exit(0);
        else if (key.getKeyType() == KeyType.Enter) {
            return 1;
        }
        return -1;
    }

    public int GameInput(int buttonSelected) throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        if (key.getKeyType() == KeyType.Backspace && bet.length() > 0 && !betEnded) {
            UserInput.setCredit(UserInput.getCredit()+Integer.parseInt(UserInput.getBet().toString()));
            bet.deleteCharAt(bet.length() - 1);
            if(!bet.toString().equals("")){
                UserInput.setCredit(UserInput.getCredit()-Integer.parseInt(UserInput.getBet().toString()));
            }
        } else if (key.getKeyType() == KeyType.Character && !betEnded) {
            char character = key.getCharacter();
            if (character == ' ') {
                return buttonSelected; // Space bar pressed (simulate button click)
            } else if (Character.isDigit(character) && bet.length() < 6) {
                if (bet.toString().equals("") && UserInput.getCredit() > 0){
                    bet.append(character);
                    UserInput.setCredit(UserInput.getCredit()-Integer.parseInt(UserInput.getBet().toString()));
                } else if(Integer.parseInt(UserInput.getBet().toString())*10 + Character.getNumericValue(character) <= UserInput.getCredit()+ Integer.parseInt(UserInput.getBet().toString())){
                    UserInput.setCredit(UserInput.getCredit()+Integer.parseInt(UserInput.getBet().toString()));
                    bet.append(character);
                    UserInput.setCredit(UserInput.getCredit()-Integer.parseInt(UserInput.getBet().toString()));
                }
            }
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            System.exit(0);
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'e')
            return 5; //Return to main menu
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        else if (UserInput.getBet().toString() != "" && betEnded) {
            if (key.getKeyType() == KeyType.ArrowUp) {
                buttonSelected = (buttonSelected - 2 + 4) % 4;
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                buttonSelected = (buttonSelected + 2) % 4;
            } else if (key.getKeyType() == KeyType.ArrowLeft) {
                buttonSelected = (buttonSelected - 1 + 2) % 2 + (buttonSelected / 2) * 2;
            } else if (key.getKeyType() == KeyType.ArrowRight) {
                buttonSelected = (buttonSelected + 1) % 2 + (buttonSelected / 2) * 2;
            } else if (key.getKeyType() == KeyType.Enter) {
                return 4;
            }
        }
        if (key.getKeyType() == KeyType.Enter && UserInput.getBet().toString() != ""){
            betEnded = true;
        }
        return buttonSelected;
    }

    public int playInput() throws IOException {
        KeyStroke key = gui.getScreen().readInput();
        betEnded = false;
        //adicionar bet ao last10games
        setBet(new StringBuilder()); //limpar a bet
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            System.exit(0);
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'y')
            return 0;
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'n')
            return 1;
        else if (key.getKeyType() == KeyType.EOF)
            return -1;
        return -1;
    }
}
