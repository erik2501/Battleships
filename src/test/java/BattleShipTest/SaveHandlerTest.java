package BattleShipTest;


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BattleShip.Game;
import BattleShip.SaveHandler;


public class SaveHandlerTest {

	private Game game;
	private Game gameAI;
	private SaveHandler saveHandler = new SaveHandler();
	
	@BeforeEach
	private void setup() {
		game = new Game();
		game.setShip(true, game.getTile(0, 0), 5);
		game.setShip(true, game.getTile(2, 0), 4);
		game.setShip(false, game.getTile(4, 0), 3);
		game.setShip(false, game.getTile(4, 2), 2);
		game.setShip(false, game.getTile(4, 6), 1);
		
		gameAI = new Game();
		gameAI.setRandomShips();
	}
	
	@Test
	public void testLoadandSave() {
		Game compareGame = game;
		Game compareGameAI = gameAI;
		try {
			saveHandler.save(game, gameAI);
		}catch (Exception e){
			fail("kunne ikke lagre fil");
		}
		
		try {
			assertEquals(compareGame.toString(), saveHandler.loadGame().toString());
			assertEquals(compareGameAI.toString(), saveHandler.loadGameAI().toString());
		} catch (FileNotFoundException e) {
			fail("Kunne ikke laste inn fil");
		}
	}

}
