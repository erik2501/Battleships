package BattleShipTest;

import BattleShip.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


public class TileTest {
	private Tile tile;
	
	@BeforeEach
	public void setup() {
		tile = new Tile(0,0);
	}
	
	
	@Test
	@DisplayName ("Tester at konstruktøren får riktige koordinater")
	public void testConstructor(){
		assertEquals(0,tile.getX());
		assertEquals(0,tile.getY());
	}
	
	
	@Test
	public void testSetTypes() {
		tile.setSea();
		assertTrue(tile.isSea());
		
		tile.setShip();
		assertTrue(tile.isShip());
		
		tile.setHit();
		assertTrue(tile.isHit());
		
		tile.setMiss();
		assertTrue(tile.isMiss());
		
	}
	
}
