
public class HangMan {
	protected String secretWord; // To store the secret word
	protected int guessesRemaining; // to store the number of guess for the user
	protected String currentState = ""; // store the current guessing situation
	protected String guessHistory = ""; // store the letters user has tried
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
		return guessHistory;
	}
	
	public String displayGameState() {
		return currentState;
	}
	
	public boolean isRepeatInput(char c)
    {
    	for (int i = 0; i < guessHistory.length(); i++) {
    		if (guessHistory.charAt(i) == c) return true;
    	}
    	return false;
    }
}
