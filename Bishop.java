
public class Bishop extends Movable implements Constants, ChessPiece  {
	
	public Bishop(int x,int y, int clr){
		position.setPosn(x,y);
		color=clr;
		state=ACTIVE;
		
	}

	@Override
	public boolean validateMove(Location destination) {
		/*
		 * 		A Bishop can move any squares diagonally on all diagonals
		 * 			So I just checked the absolute value of the moves in x and y directions 
		 * */
		Board chessBoard=Board.getInstance(color);
		int delx=destination.getPosnX()-position.getPosnX();
		int dely=destination.getPosnY()-position.getPosnY();
		int finx=destination.getPosnX();
		int finy=destination.getPosnY();
		int inx=position.getPosnX();
		int iny=position.getPosnY();
		if (Math.abs(destination.getPosnX()-position.getPosnX())== Math.abs(destination.getPosnY()-position.getPosnY())){
			if(delx>0){
				System.out.println("here na");
				if(dely>0){
					System.out.println("here na");
					while (inx!=finx-1){
						inx++;
						iny++;
						if(!chessBoard.isEmpty(inx, iny )){
							return false;
						}
					}
					return true;
				}else if (dely<0){
					while (inx!=finx-1){
						inx++;
						iny--;
						if(!chessBoard.isEmpty(inx, iny )){
							return false;
						}
					}
					return true;
				}
			}else if(delx<0){
				if(dely>0){
					while (inx!=finx+1){
						inx--;
						iny++;
						if(!chessBoard.isEmpty(inx, iny )){
							return false;
						}
						
					}
					return true;
				}else if (dely<0){
					while (inx!=finx+1){
						inx--;
						iny--;
						if(!chessBoard.isEmpty(inx, iny )){
							return false;
						}
						
					}
				return true;
				}
			}
		}else 
			return false;
		return true;
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
						//chessBoard.setPieceAt(x, y, a)
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
