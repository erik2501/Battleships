package BattleShipTest;


import BattleShip.Game;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;



public class GameTest {

	private Game game;

	
	@BeforeEach
	private void setup() {
		game = new Game();
		game.setShip(true, game.getTile(0, 0), 5);
		game.setShip(true, game.getTile(2, 0), 4);
		game.setShip(false, game.getTile(4, 0), 3);
		game.setShip(false, game.getTile(4, 2), 2);
		game.setShip(false, game.getTile(4, 6), 1);
	}
	
	@Test
	
	public void testConstructor() {
		game = new Game();
		
		for (int y = 0; y < game.getWidth(); y++) {
			for (int x = 0; x < game.getHeight(); x++) {
				assertEquals(game.getTile(x, y).getType(), '-');
			}
		}
	}
	
	@Test
	public void setShip() {
		game = new Game();
		game.setShip(true, game.getTile(0, 0), 2);
		game.setShip(false, game.getTile(1, 0), 2);
		assertEquals(game.getTile(0, 0).getType(), 'x');
		assertEquals(game.getTile(0, 1).getType(), 'x');
		assertEquals(game.getTile(1, 0).getType(), 'x');
		assertEquals(game.getTile(2, 0).getType(), 'x');
	}
	
	@Test
	@DisplayName("Tester at du ikke kan sette skip utenfor brettet")
	public void testInvalidShip() {
		assertThrows(
				IllegalArgumentException.class,
				() -> game.setShip(true, game.getTile(9, 9), 5),
				"IllegalArgumentException kastes fordi vi prøver å sette skipet utenfor brettet"
			);
	}

	
	@Test
	@DisplayName("Tester at du kan skyte på en tile og dermed endre character")
	public void isHit() {
		game.isHit(game.getTile(0, 0));
		game.isHit(game.getTile(1, 0));
		assertEquals(game.getTile(0, 0).getType(), 'O');
		assertEquals(game.getTile(1, 0).getType(), 'M');

	}
	
	@Test
	public void testNumberOfShips() {
		assertTrue(game.numberOfShips() == 15);
	}
	
	@Test
	public void testDeleteShips() {
		game.deleteShips();
		
		for (int y = 0; y < game.getWidth(); y++) {
			for (int x = 0; x < game.getHeight(); x++) {
				assertEquals(game.getTile(x, y).getType(), '-');
			}
			}
		
	}
	
	@Test
	public void testSetRandomShips() {
		game = new Game();
		
		game.setRandomShips();
		assertTrue(game.numberOfShips() == 15);
	}
	
	@Test
	public void testIsGameWon() {
		game.isHit(game.getTile(0, 0));
		game.isHit(game.getTile(0, 1));
		game.isHit(game.getTile(0, 2));
		game.isHit(game.getTile(0, 3));
		game.isHit(game.getTile(0, 4));
		game.isHit(game.getTile(2, 0));
		game.isHit(game.getTile(2, 1));
		game.isHit(game.getTile(2, 2));
		game.isHit(game.getTile(2, 3));
		game.isHit(game.getTile(4, 0));
		game.isHit(game.getTile(5, 0));
		game.isHit(game.getTile(6, 0));
		game.isHit(game.getTile(4, 2));
		game.isHit(game.getTile(5, 2));
		game.isHit(game.getTile(4, 6));
		
		assertTrue(game.isGameWon());
		
	}
	
	
	@Test
	public void testRandomTile() {
		for (int i = 0; i < 100; i++) {
			game.isHit(game.randomTile());
		}
		int numberOfHits = 0;
		int numberOfMisses = 0;
		
		for (int y = 0; y < game.getWidth(); y++) {
			for (int x = 0; x < game.getHeight(); x++) {
				if (game.getTile(x, y).isMiss()) {
					numberOfMisses++;
				}
				if (game.getTile(x, y).isHit()) {
					numberOfHits++;
				}
			}
			}
		
		assertEquals(numberOfHits, 15);
		assertEquals(numberOfMisses, 85);
	}

	
	
}
