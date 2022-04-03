package BattleShip;

import java.io.FileNotFoundException;

public interface SaveAndLoad {
	
	
	public void save(Game game, Game AI);
	public Game loadGame() throws FileNotFoundException ;
	public Game loadGameAI() throws FileNotFoundException;
	
	
	
}
