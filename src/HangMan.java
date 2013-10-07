import java.util.ArrayList;
import java.util.List;


public class HangMan {
	protected String secretWord = ""; // To store the secret word
	protected int guessesRemaining; // to store the number of guess for the user
	protected String currentState = ""; // store the current guessing situation
	
	protected List<Character> guessHistory = new ArrayList<Character>(); // store the letters user has tried
	protected char guess; // the letter the user guess right now
	
	public HangMan() {
		super();
	}
	
	public String getSecretWord()
    {
        return secretWord;
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
}
