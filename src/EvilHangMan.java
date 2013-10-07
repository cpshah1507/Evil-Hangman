import java.util.*;
import java.io.*;


public class EvilHangMan extends HangMan implements HangmanGame {

	//private String[] wordlist = new String[235000];// to store the dictionary
	private ArrayList<String> wordlist = new ArrayList<String>();
	private int numWords = 0;// count the number of possible secret words.
	private int secretStringLength;// the length of the secret string
	private boolean guessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		guessesRemaining = numGuesses;
		secretStringLength = StringLength;
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int i = 0;
		while (scanner.hasNext()) {
			String temp = scanner.nextLine().toUpperCase();
			if (temp.length() == StringLength) {
				wordlist.add(temp);
				numWords++;
			}
		}

		for (i = 0; i < StringLength; i++) {
			currentState += "_ ";
		}
		scanner.close();

	}

	public int numLettersRemaining() {
		return 26; // because they never get one right!
	}

	public boolean isWin() {
		return false;
	}

	public boolean gameOver() {
		if (guessesRemaining == 0)
			return true;
		else
			return false;
	}

	public boolean makeGuess(char ch) {

		System.out.println("makeGuess: " + ch + "; numWords=" + numWords);
		guessResult = false;
		guess = ch;
		if (Character.isLetter(ch) && !isRepeatInput(ch)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
			
			int tempWordNum = 0;
			for (int i = 0; i < numWords; i++) {
				for (int j = 0; j < secretStringLength; j++) {
					if(wordlist.get(i).charAt(j) == ch){
						break;
					} 
					else {
						if (j == secretStringLength - 1) {
							if (wordlist.get(i).charAt(j) != ch) {
								tempWordNum++;
							}
						}
					}
				}
			}
			// we choose the words that don't contain the letter the user
			// guessed, and they will be the new possible secret words.
			if(tempWordNum > 0)
			{
				for (int i = 0; i < wordlist.size(); i++) {
					for (int j = 0; j < secretStringLength; j++) {
						if (wordlist.get(i).charAt(j) == ch) {
							wordlist.remove(i);
							--i;
							break;
						} 
					}
				}
			}
			
			if (tempWordNum == 0) {
				System.out.println("tempWordNum is zero!");
				secretWord = wordlist.get(0);
				guessResult = true;
			} else {
				numWords = tempWordNum;
				guessesRemaining--;
				guessResult = false;
			}
			
			secretWord = wordlist.get(0);
			if (!guessResult) {
				guessHistory.add(guess);
			}

		} else return false;
		
		return guessResult;
	}
}