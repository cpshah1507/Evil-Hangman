import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class HangMan {
	protected String secretWord = ""; // To store the secret word
	protected int guessesRemaining; // to store the number of guess for the user
	protected String currentState = ""; // store the current guessing situation
	
	protected List<Character> guessHistory = new ArrayList<Character>(); // store the letters user has tried
	protected char guess; // the letter the user guess right now
	
	/*public HangMan() {
		super();
	}*/
	
	public String getSecretWord()
    {
        return secretWord;
    }
	
	public void setSecretWord(String secretword)
	{
		secretWord = secretword;
	}
	
	public int numGuessesRemaining()
    {
        return guessesRemaining;
    }
	
	public String lettersGuessed() {
		StringBuilder lettersGuessed = new StringBuilder(guessHistory.size());
	    for(Character ch: guessHistory)
	    {
	    	lettersGuessed.append(ch);
	    }
	    return lettersGuessed.toString();
	}
	
	public String displayGameState() {
		return currentState;
	}
	
	public boolean isRepeatInput(char c)
    {
    	for (int i = 0; i < guessHistory.size(); i++) {
    		if (guessHistory.get(i) == c) return true;
    	}
    	return false;
    }
	
	/*
     * This handles the logic of sending info to the Game object.
     */
	
	public static boolean controller(char inputLetter, boolean isEvil, JLabel label2, JLabel label3, JLabel result, JFrame frame, HangmanGame game)
	{
		//handle the user choice, and pass the data to the model
        char nextLetter = Character.toUpperCase(inputLetter);
        if (game.makeGuess(nextLetter))
        {
            if (isEvil)//judge whether the hangman is evil
            {
                //if in the evil statement, and the user guess right, 
            	// it means it is the time to turn the evil to the regular hangmam
                result.setText("Yes!");
                String secretString = game.getSecretWord();
                int guessesRemaining = game.numGuessesRemaining();
                String letterHistory = game.lettersGuessed();
                game = new NormalHangMan(secretString, guessesRemaining,letterHistory);//turn the evil to regular hangman
                isEvil = false; 
                game.makeGuess(nextLetter);//re-value the user guess when turn to the regular hangman for the first time
            }
            else
            {
            	result.setText("Yes!");
            }
        }
        else
        {
        	result.setText("Nope!");
        }

        label2.setText("Secret Word: "+game.displayGameState());
        label3.setText(String.valueOf("Guesses Remaining: "+ game.numGuessesRemaining()));
        if (game.gameOver())
        {
            if (game.isWin())
            {
                new GUI_Winner(game.displayGameState(),frame);
            }
            else
            {
                new GUI_Loser(game.getSecretWord(),frame);
            }
        }
        return isEvil;
	}
	
	public static HangmanGame createNormalHangMan(HangmanGame game,char inputLetter)
	{
		game = new NormalHangMan(game.getSecretWord(), game.numGuessesRemaining(),game.lettersGuessed());//turn the evil to regular hangman
		game.makeGuess(inputLetter);
		return game;
	}
}
