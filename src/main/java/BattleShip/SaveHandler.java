package BattleShip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveHandler implements SaveAndLoad {

	
	public final static String SAVE_FOLDER = "src/main/java/BattleShip/";
	
	public void save (Game game, Game gameAI){
		try  (PrintWriter writer= new PrintWriter(getFilePath("FIL1"))){
			for (int y = 0; y < game.getWidth(); y++) {
				for (int x = 0; x < game.getHeight(); x++) {
					writer.print(game.getTile(x,y).getType());
				}
				}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try  (PrintWriter writer= new PrintWriter(getFilePath("FIL2"))){
			for (int y = 0; y < gameAI.getWidth(); y++) {
				for (int x = 0; x < gameAI.getHeight(); x++) {
					writer.print(gameAI.getTile(x,y).getType());
				}
				}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Game loadGame() throws FileNotFoundException {
		try (Scanner scanner = new Scanner (new File(getFilePath("FIL1")))){
				Game game = new Game();
				
				String board = scanner.next();
				
				for (int y = 0; y < game.getWidth(); y++) {
					for (int x = 0; x < game.getHeight(); x++) {
						char symbol = board.charAt(y* game.getWidth() + x);
						game.getTile(x, y).setType(symbol);
					}
					}
			return game;
			}
	}
	
	public Game loadGameAI() throws FileNotFoundException {
		try (Scanner scanner = new Scanner (new File(getFilePath("FIL2")))){
			Game gameAI = new Game();
			
			String board = scanner.next();
			
			for (int y = 0; y < gameAI.getWidth(); y++) {
				for (int x = 0; x < gameAI.getHeight(); x++) {
					char symbol = board.charAt(y* gameAI.getWidth() + x);
					gameAI.getTile(x, y).setType(symbol);
				}
				}
		return gameAI;
		}
	}
	
	
	private static String getFilePath(String filename) {
		return SAVE_FOLDER + filename + ".txt";
	}
	

}
