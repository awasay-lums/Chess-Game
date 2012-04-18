
public class Queen extends Movable implements Constants, ChessPiece {
	
	public Queen(int x,int y, int clr){
		position.setPosn(x,y);
		color=clr;
		state=ACTIVE;
		
	}

	public boolean validateMove(Location destination) {
		
		Board chessBoard=Board.getInstance(color);
		int delx=destination.getPosnX()-position.getPosnX();
		int dely=destination.getPosnY()-position.getPosnY();
		int finx=destination.getPosnX();
		int finy=destination.getPosnY();
		int inx=position.getPosnX();
		int iny=position.getPosnY();
		if (Math.abs(destination.getPosnX()-position.getPosnX())== Math.abs(destination.getPosnY()-position.getPosnY())){
			if(delx>0){
				if(dely>0){
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
		}
		else if(position.getPosnX()== destination.getPosnX()){
			if(dely>0){
				while(iny!=finy-1){
					iny++;
					if(!chessBoard.isEmpty(inx, iny )){
						return false;
					}
				}
				return true;
			}else if(dely<0){
				while(iny!=finy+1){
					iny--;
					if(!chessBoard.isEmpty(inx, iny )){
						return false;
					}
				}
				return true;
			}
		}
		else if (position.getPosnY()== destination.getPosnY()){
			if(delx>0){
				while(inx!=finx-1){
					inx++;
					if(!chessBoard.isEmpty(inx, iny )){
						return false;
					}
				}
				return true;
			}else if(delx<0){
				while(inx!=finx+1){
					inx--;
					if(!chessBoard.isEmpty(inx, iny )){
						return false;
					}
				}
				return true;
			}
			return true;
		}
		else
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
		// TODO Auto-generated method stub
		state=INACTIVE;
		killer =a;
		return null;
	}
	
	public boolean hasCheckOnOpossingKing(Location positionOfOpposingKing) {
		if(state==INACTIVE)
			return false;
		if(validateMove(positionOfOpposingKing))
			return true;
		return false;

	}
	
	

}
