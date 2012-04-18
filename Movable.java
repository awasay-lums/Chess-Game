/*
 * 	The main class inherited by all the chess pieces. It contains their:
 * location, state and color as well as the generic getter and setter functions 
 * that are common to all the chess pieces.
 * 
 * */

public class Movable implements Constants{
	
	public Location position = new Location();
	boolean state; // true if alive, false otherwise.
	ChessPiece killer;
	int color;// BLACK or WHITE
	
		
	public boolean isActive() {
		
		return state;
	}

	public boolean isBlack() {
		
		return (color==BLACK);
	}

	public boolean isWhite() {
		
		return (color==WHITE);
	}
	

	public Location getLocation(){
		return position;
	}
	public void setinActive(){
		state=INACTIVE;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setPosition(Location fin){
		
		position=fin;
	}
	
	public void setState(boolean a){
		state=a;
	}
	

}
