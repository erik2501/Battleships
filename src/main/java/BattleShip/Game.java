package BattleShip;

import java.util.Random;

public class Game {
	private int height;
	private int width;
	private Tile[][] board;
	private boolean HorVert;
	
	
	
	public Game() {
		this.height = 10;
		this.width = 10;
		
		this.board = new Tile[10][10];
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				board[y][x] = new Tile(x,y);
			}
	}
	}
	
	public void isHit(Tile hit) {
		if(isValidTile(hit.getX(),hit.getY())) {
			if (hit.isShip()) {
				hit.setHit();
			}
			if (hit.isSea()) {
				hit.setMiss();
			}
		}else {
			throw new IllegalArgumentException("Brikken du ønsker å treffe er utenfor brettet");
		}
	}
	
	public void playGame(Tile tile) {
		if (isGameWon() == false) {
			this.isHit(tile);
		}
	}
	
	public void playGameAI(Tile tile) {
		if (isGameWonAI() == false) {
			this.isHitAI(tile);
		}
	}
	
	public void isHitAI(Tile hit) {
		if(isValidTile(hit.getX(),hit.getY())) {
			if (hit.isShip()) {
				hit.setHit();
			}
			if (hit.isSea()) {
				hit.setMiss();
			}
		}else {
			throw new IllegalArgumentException("Brikken du ønsker å treffe er utenfor brettet");
		}
	}
	
	public boolean isGameStarted() {
		int a = 0;
	
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (this.getTile(x, y).isShip() == true || this.getTile(x, y).isHit() == true) {
					a++;
				}
			}
	}
		return (a == 15);
	}
	
	public boolean isGameWon() {
		int amountOfHits = 0;
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
			if (this.getTile(x, y).isHit()) {
				amountOfHits++;
			}
			}
			}
		return amountOfHits == 15;
	}
	
	public boolean isGameWonAI() {
		int amountOfHits = 0;
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
			if (this.getTile(x, y).isHit()) {
				amountOfHits++;
			}
			}
			}
		return amountOfHits == 15;
	}
	
	public void setShip(Boolean horvert ,Tile start, int size){	
		
		if (horvert == true) {
			setShipVert(start, size);
		}
		if (horvert == false) {
			setShipHor(start, size);
		}	
		
	}

	
	public void setHor() {
		this.HorVert = false;
	}
	
	public void setVert() {
		this.HorVert = true;
	}
	public boolean getHorVert() {
		return this.HorVert;
	}
	
	public void setShipAi(boolean horvert, Tile start, int size) {
		if (horvert == true) {
			for (int y = 0; y < size; y++) {
				(getTile(start.getX(), start.getY()+y)).setShip();
			}
		}
		if (horvert == false) {
			for (int y = 0; y < size; y++) {
				(getTile(start.getX()+y, start.getY())).setShip();
			}
		}
	}
	
	private boolean validShipPosVert(Tile start, int size) {
		for (int y = 0; y < size; y++) {
			if (getTile(start.getX(), start.getY()+y).getType() == 'x') {
				return false;
		}
		}
		if (start.getX() > 9  || start.getX() < 0 || start.getY() + size > 10) {
			return false;
		}
			return true;
		}
	
	private boolean validShipPosHor(Tile start, int size) {
		for (int y = 0; y < size; y++) {
			if (getTile(start.getX()+y, start.getY()).getType() == 'x') {
				return false;
		}
		}
		if (start.getY() > 9  || start.getY() < 0 || start.getX() + size > 10) {
			return false;
		}
			return true;
		}

	
	
	private void setShipVert(Tile start, int size) {
		if (validShipPosVert(start,size) == false) {
			throw new IllegalArgumentException("To skip kan ikke stå oppå hverandre og de kan ikke ligge utenfor brettet.");
		}

		for (int y = 0; y < size; y++) {
			(getTile(start.getX(), start.getY()+y)).setShip();
		}
	}
	
	private void setShipHor(Tile start, int size) {
		if (validShipPosHor(start,size) == false) {
			throw new IllegalArgumentException("To skip kan ikke stå oppå hverandre og de kan ikke ligge utenfor brettet.");
		}
		
		for (int y = 0; y < size; y++) {
			(getTile(start.getX()+y, start.getY())).setShip();
		}
	}
	public void deleteShips() {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				getTile(x,y).setSea();
			}
		}
	}
	
	public int getHeight() {
		return height;
	}

	public int numberOfShips() {
		int a = 0;
		
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (this.getTile(x, y).isShip() == true || this.getTile(x, y).isHit() == true) {
					a++;
				}
			}
	}
		return a;
	}

	public int getWidth() {
		return width;
	}
	
	public void setRandomShips() {
		Random ra = new Random();
		boolean ait = false;
		

		
		while (ait == false) {

			Tile t1;
			Tile t2;
			Tile t3;
			Tile t4;
			Tile t5;
			
			
			boolean horvert_1 = ra.nextBoolean();
			if (horvert_1 == true) {
				t1 = new Tile(ra.nextInt(9),ra.nextInt(4));
				setShipAi(horvert_1,t1,5);
			} else {
				t1 = new Tile(ra.nextInt(4),ra.nextInt(9));
				setShipAi(horvert_1,t1,5);
			}
			
			boolean horvert_2 = ra.nextBoolean();
			if (horvert_2 == true) {
				t2 = new Tile(ra.nextInt(9),ra.nextInt(5));
				setShipAi(horvert_2,t2,4);
			} else {
				t2 = new Tile(ra.nextInt(5),ra.nextInt(9));
				setShipAi(horvert_2,t2,4);
			}
			
			boolean horvert_3 = ra.nextBoolean();
			if (horvert_3 == true) {
				t3 = new Tile(ra.nextInt(9),ra.nextInt(6));
				setShipAi(horvert_3,t3,3);
			} else {
				t3 = new Tile(ra.nextInt(6),ra.nextInt(9));
				setShipAi(horvert_3,t3,3);
			}
			
			boolean horvert_4 = ra.nextBoolean();
			if (horvert_4 == true) {
				t4 = new Tile(ra.nextInt(9),ra.nextInt(7));
				setShipAi(horvert_4,t4,2);
			} else {
				t4 = new Tile(ra.nextInt(7),ra.nextInt(9));
				setShipAi(horvert_4,t4,2);
			}
			
			boolean horvert_5 = ra.nextBoolean();
			if (horvert_5 == true) {
				t5 = new Tile(ra.nextInt(9),ra.nextInt(8));
				setShipAi(horvert_5,t5,1);
			} else {
				t5 = new Tile(ra.nextInt(8),ra.nextInt(9));
				setShipAi(horvert_5,t5,1);
			}

			

			int numberOfShips = 0;
			
			for (int y = 0; y < getHeight(); y++) {
				for (int x = 0; x < getWidth(); x++) {
					if(getTile(x,y).getType() == 'x') {
						numberOfShips += 1;
					}
				}
					
				}
			if (numberOfShips == 15) {
				ait = true;
			} else {
				deleteShips();
			}
			}	
		
	}
	
	public Tile randomTile() {
		Random ra = new Random();
		int x = ra.nextInt(10);
		int y = ra.nextInt(10);
		

		while (getTile(x,y).isHit() || getTile(x,y).isMiss()) {
			x = ra.nextInt(10);
			y = ra.nextInt(10);
		}
		return getTile(x,y);
	
	}

	
	public boolean isValidTile(int x, int y) {
		return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
	}

	public String toString() {
		String stringBoard = "";
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				stringBoard += getTile(x,y);
			}
			stringBoard += "\n";
		}
		return stringBoard;
	}
	

	
	public Tile getTile(int x, int y) {
		if (!isValidTile(x,y)) {
			throw new IllegalArgumentException("Brikken er utenfor brettet");
		}
		return board[y][x];
	}
	

}
