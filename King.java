
public class King  extends Movable implements Constants, ChessPiece  {
	
	public King(int x,int y, int clr){
		position.setPosn(x,y);
		color=clr;
		state=ACTIVE;
		
	}

	public boolean validateMove(Location destination) {
		
		
		/*
		 * 	Since castling is not allowed, the king can only move once upward
		 * 	 downward or diagonally
		 * */
		
		int delX=Math.abs(destination.getPosnX()-position.getPosnX());
		int delY=Math.abs(destination.getPosnY()-position.getPosnY());
		
		if (delX==1 && delY==0){
			return true;
		}
		else if (delX==0 && delY==1){
			return true;
		}
		else if (delX==1 && delY==1){
			return true;
		}
		else 			
			return false;
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
	

	public ChessPiece kill(ChessPiece a) {
		// TODO Auto-generated method stub
		state=INACTIVE;
		killer =a;
		return null;
	}
	
	public boolean hasCheckOnOpossingKing(Location positionOfOpposingKing) {
		if(state==INACTIVE) return false;
		if(validateMove(positionOfOpposingKing))
			return true;
		return false;

	}

}
