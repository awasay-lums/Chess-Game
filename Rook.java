
public class Rook extends Movable implements Constants, ChessPiece {
	
	public Rook(int x,int y, int clr){
		position.setPosn(x,y);
		color=clr;
		state=ACTIVE;
		
	}

	@Override
	public boolean validateMove(Location destination) {
		int delx=destination.getPosnX()-position.getPosnX();
		int dely=destination.getPosnY()-position.getPosnY();
		int finx=destination.getPosnX();
		int finy=destination.getPosnY();
		int inx=position.getPosnX();
		int iny=position.getPosnY();
		
		Board chessBoard= Board.getInstance(color);
		
		if(delx==0){
			if(dely>0){
				while(iny!=finy-1){
					iny++;
					if(!chessBoard.isEmpty(inx, iny))
						return false;
					
				}
				return true;
			}else if(dely<0){
				while(iny!=finy+1){
					iny--;
					if(!chessBoard.isEmpty(inx, iny))
						return false;
					
				}
				return true;
				
			}
		}

		if (destination.getPosnX()>position.getPosnX()){
			for(int i=position.getPosnX()+1; i<destination.getPosnX();i++ ){
				if (!chessBoard.isEmpty(i,destination.getPosnY())){
					return false;
				}
			}
		}else if (destination.getPosnX()<position.getPosnX()){
			for(int i=position.getPosnX()-1; i>destination.getPosnX();i-- ){
				if (!chessBoard.isEmpty(i,destination.getPosnY())){
					return false;
				}
			}	
		}
			
		
		if (!chessBoard.isEmpty(destination.getPosnX(), destination.getPosnY())){
			if(chessBoard.getColorAt(destination.getPosnX(), destination.getPosnY())==color){
				return false;
			}else {
				//System.out.println("killed");
			}
		}
		
		/*
		 * 	A rook can move horizontally as well as vertically any number of squares
		 * */
		
		if (position.getPosnX()== destination.getPosnX() && position.getPosnY()== destination.getPosnY())
			
			return false;
		else if (position.getPosnX()== destination.getPosnX() && position.getPosnY()!= destination.getPosnY() && color==WHITE ){
			return true;
		}
		else if (position.getPosnX()== destination.getPosnX() && position.getPosnY()!= destination.getPosnY() && color==BLACK ){
			return true;
		}
		
		else if (position.getPosnY()== destination.getPosnY()){
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
