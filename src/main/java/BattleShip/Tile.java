package BattleShip;



public class Tile {

	
	private char type = '-';
	private int x;
	private int y;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setType(char symbol) { //Her trenger jeg ikke noen valideringsmetode fordi den kun brukes i lasting av fil. altså ville dette vært overflødig.
		type = symbol;
	}
	
	public void setSea() {
		type = '-';
	}
	
	public void setHit() {
		type = 'O';
	}
	
	public void setMiss() {
		type = 'M';
	}
	
	
	public void setShip() {
		type = 'x';
	}
	
	public boolean isSea() {
		return type == '-';
	}
	
	public boolean isMiss() {
		return type == 'M';
	}
	public boolean isHit() {
		return type == 'O';
	}
	
	public boolean isShip() {
		return type == 'x';
	}
	public char getType() {
		return type;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
    public String toString() {
        return Character.toString(type);
    }
    
}
