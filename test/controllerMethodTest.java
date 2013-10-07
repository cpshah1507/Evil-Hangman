import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;


public class controllerMethodTest {
	private GUI_PlayGame GUI_PlayGame;
	private final String WORD = "NUMBSKULLS";

	@Before
	public void setUp() throws Exception {
		GUI_PlayGame = new GUI_PlayGame(10,8);
		GUI_PlayGame.frame = new JFrame();	
		GUI_PlayGame.label2 = new JLabel();
		GUI_PlayGame.label3 = new JLabel();	
		GUI_PlayGame.result = new JLabel();  
		GUI_PlayGame.game.setSecretWord(WORD);
	}
	
	@Test
	public void testBranchCase1() {
		
		// Test Case for Branch : game.makeGuess(nextLetter) == false
		GUI_PlayGame.inputLetter = 'A';
		
		// Controller method returns value of isEvil
		assertTrue(HangMan.controller(GUI_PlayGame.inputLetter, GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game));
		assertEquals("Nope!",GUI_PlayGame.result.getText());
		assertEquals("_ _ _ _ _ _ _ _ _ _ ", GUI_PlayGame.game.displayGameState());
		assertEquals(7, GUI_PlayGame.game.numGuessesRemaining());
		
		HangMan.controller('E', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		HangMan.controller('I', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		HangMan.controller('O', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		
		// Test Case for Branch : game.makeGuess(nextLetter) == true and isEvil == true
		GUI_PlayGame.inputLetter = 'U';
		assertFalse(HangMan.controller(GUI_PlayGame.inputLetter, GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game));
		GUI_PlayGame.game = HangMan.createNormalHangMan(GUI_PlayGame.game,GUI_PlayGame.inputLetter);
		
		assertEquals("Yes!",GUI_PlayGame.result.getText());
		assertEquals("_ U _ _ _ _ U _ _ _ ", GUI_PlayGame.game.displayGameState());
		assertEquals(4, GUI_PlayGame.game.numGuessesRemaining());
		
		// Test Case for Branch : game.makeGuess(nextLetter) == true and isEvil == false
		GUI_PlayGame.inputLetter = 'N';
		assertFalse(HangMan.controller(GUI_PlayGame.inputLetter, GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game));
		assertEquals("Yes!",GUI_PlayGame.result.getText());
		assertEquals("N U _ _ _ _ U _ _ _ ", GUI_PlayGame.game.displayGameState());
		assertEquals(4, GUI_PlayGame.game.numGuessesRemaining());
	}
	
	@Test
	public void testBranchCase2() {
		GUI_PlayGame.inputLetter = 'A';
		
		// Test case for branch: game.gameOver() == false
		assertTrue(HangMan.controller(GUI_PlayGame.inputLetter, GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game));
		assertFalse(GUI_PlayGame.game.gameOver());
	
		HangMan.controller('B', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		HangMan.controller('C', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		HangMan.controller('D', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		HangMan.controller('E', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		HangMan.controller('F', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		HangMan.controller('G', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game);
		
		// Test case for branch: game.gameOver() == true and game.isWin() == false
		assertTrue(HangMan.controller('H', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game));
		assertTrue(GUI_PlayGame.game.gameOver());
		assertFalse(GUI_PlayGame.game.isWin());
	}
	
	@Test
	public void testBranchCase3() {
		
		GUI_PlayGame.game.makeGuess('A');
		GUI_PlayGame.game.makeGuess('E');
		GUI_PlayGame.game.makeGuess('I');
		GUI_PlayGame.game.makeGuess('O');
		GUI_PlayGame.game.makeGuess('U');
		
		// Normal Hangman Mode will be starting from here
		GUI_PlayGame.game = HangMan.createNormalHangMan(GUI_PlayGame.game,'U');
		GUI_PlayGame.isEvil = false;
		
		GUI_PlayGame.game.makeGuess('N');
		GUI_PlayGame.game.makeGuess('M');
		GUI_PlayGame.game.makeGuess('B');
		GUI_PlayGame.game.makeGuess('S');
		GUI_PlayGame.game.makeGuess('K');
		
		// Test case for branch: game.gameOver() == true and game.isWin() == true
		assertFalse(HangMan.controller('L', GUI_PlayGame.isEvil, GUI_PlayGame.label2, GUI_PlayGame.label3, GUI_PlayGame.result, GUI_PlayGame.frame, GUI_PlayGame.game));
		assertTrue(GUI_PlayGame.game.gameOver());		
		assertTrue(GUI_PlayGame.game.isWin());
	}
}
