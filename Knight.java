
public class Knight extends Movable implements Constants, ChessPiece {
	
	public Knight(int x,int y, int clr){
		position.setPosn(x,y);
		color=clr;
		state=ACTIVE;
		
	}
	

	public boolean validateMove(Location destination) {
		/*
		 * 		Check to see if the position being moved to is unoccupied or occupied by the other colro
		 * */
		boolean status;
		
		
			
		/*
		 * 	A knight can move once horizontally and twice vertically or vice versa
		 * 		Every other move is false
		 * */
				
		if (Math.abs( destination.getPosnX()-position.getPosnX())==2 && Math.abs(destination.getPosnY()-position.getPosnY())==1){
			
			status= true;
		}
		else if (Math.abs( destination.getPosnX()-position.getPosnX())==1 && Math.abs(destination.getPosnY()-position.getPosnY())==2){
			status= true;
		}
		else 
			status= false;
		
		return status;
	}
		
	


	public boolean makeMove(Location destination) {
		
		Location initial= new Location(position.getPosnX(), position.getPosnY());
		
		if(validateMove(destination)){
			Board chessBoard= Board.getInstance(color);
			
			if(chessBoard.isEmpty(destination.getPosnX(), destination.getPosnY())){
				
				position.setPosn(destination);
				
				if(chessBoard.isCheck(color)){
					position.setPosn(initial);
					return false;
				}
				
				return true;
			}else{
				if(chessBoard.getColorAt(destination.getPosnX(), destination.getPosnY())==color){
					return false;
				}else{
					position.setPosn(destination);
					ChessPiece in=chessBoard.getPieceAt(destination.getPosnX(), destination.getPosnY());
					chessBoard.setPieceAt(destination.getPosnX(), destination.getPosnY(),(chessBoard.getPieceAt(destination.getPosnX(), destination.getPosnY()).kill(this)));
					if(chessBoard.isCheck(color)){
						position.setPosn(initial);
						chessBoard.setPieceAt(destination.getPosnX(), destination.getPosnY(),in);
						return false;
					}
					return true;
				}
				
			}
		}else{
			return false;
		}
			
}

	@Override
	public ChessPiece kill(ChessPiece a) {
		state=INACTIVE;
		killer=a;
		return null;
	}
	
	public boolean hasCheckOnOpossingKing(Location positionOfOpposingKing) {
		if(state==INACTIVE) return false;
		if(validateMove(positionOfOpposingKing))
			return true;
		return false;

	}

}
