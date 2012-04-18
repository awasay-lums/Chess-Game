/*
 * 	the interface that is implemented by all 
 * */
public interface ChessPiece {
	/*
	 * check to see if the move is valid
	 * */
	boolean validateMove(Location destination);
	
	/*
	 * makes the move and returns true if success , otherwise false
	 * */
	boolean makeMove(Location destination);
	
	/*
	 *	Checks to see if the opposing king is checked 
	 * */
	boolean hasCheckOnOpossingKing(Location positionOfOpposingKing);
	
	/*
	 *	Active or not 
	 * */
	boolean isActive();
	
	/*
	 *	Black or not 
	 * */
	boolean isBlack();
	
	/*
	 *	White or not 
	 * */
	boolean isWhite();
	
	/*
	 * 
	 * */
	ChessPiece kill(ChessPiece a);
	
	/*
	 * 
	 */
	
	public Location getLocation();
	
	public int getColor();
	
	public void setPosition(Location l);
	
	public void setState(boolean a);
}
