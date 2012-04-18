import java.rmi.RemoteException;


public class Pawn extends Movable implements ChessPiece,Constants{
	
	public Pawn(int x,int y, int clr){
		position.setPosn(x,y);
		color=clr;
		state=ACTIVE;
		hasMoved=false;
		
	}
	
	// determines if the pawn has moved before
	boolean hasMoved;
	
	
	public boolean validateMove(Location destination) {
		/*
		 * 	Check to see if the blocks ahead are occupied, then it moves only if its a kill
		 * */
		Board chessBoard= Board.getInstance(color);
			if (!chessBoard.isEmpty(destination.getPosnX(), destination.getPosnY())){
				
				if(chessBoard.getColorAt(destination.getPosnX(), destination.getPosnY())==color){
					return false;
				}else{
					if( Math.abs(destination.getPosnX()-position.getPosnX())==1 && destination.getPosnY()-position.getPosnY()==-1 && color == BLACK){
						return true;
					}else if( Math.abs(destination.getPosnX()-position.getPosnX())==1 && destination.getPosnY()-position.getPosnY()==1 && color == WHITE){
						return true;
					}
				}
				
				
			}
		
		/*
		 * 	A pawn can only move a square ahead, if it has moved before,
		 * otherwise it has the option of going two positions ahead
		 * */
		
		if (destination.getPosnX()== position.getPosnX()){
			if(destination.getPosnY()== position.getPosnY()+1 && color==WHITE){
				hasMoved=true;
				return true;
			}else if (destination.getPosnY()== position.getPosnY()-1 && color==BLACK){
				hasMoved=true;
				return true;
			}
		}
		
		if(hasMoved==false){
			if (destination.getPosnX()== position.getPosnX()){
				if(destination.getPosnY()== position.getPosnY()+2 && color==WHITE){
					hasMoved=true;
					return true;
				}else if (destination.getPosnY()== position.getPosnY()-2 && color==BLACK){
					hasMoved=true;
					return true;
				}
			}
			return false;
			
		}
		
		return false;
	}

	
	public boolean makeMove(Location destination) {
		Location initial= new Location(position.getPosnX(), position.getPosnY());
		if(validateMove(destination)){
			Board chessBoard= Board.getInstance(color);
			if (!chessBoard.isEmpty(destination.getPosnX(), destination.getPosnY())){
				if(chessBoard.getColorAt(destination.getPosnX(), destination.getPosnY())==color){
					return false;
				}else{
					if( Math.abs(destination.getPosnX()-position.getPosnX())==1 && destination.getPosnY()-position.getPosnY()==-1 && color == BLACK){
						System.out.println("killed some");
						
						chessBoard.setPieceAt(destination.getPosnX(), destination.getPosnY(),(chessBoard.getPieceAt(destination.getPosnX(), destination.getPosnY()).kill(this)));
						position.setPosn(destination);						
						if(chessBoard.isCheck(color)){							
							position.setPosn(initial);
							return false;
						}
						return true;
					}else if( Math.abs(destination.getPosnX()-position.getPosnX())==1 && destination.getPosnY()-position.getPosnY()==1 && color == WHITE){
						position.setPosn(destination);
						chessBoard.setPieceAt(destination.getPosnX(), destination.getPosnY(),(chessBoard.getPieceAt(destination.getPosnX(), destination.getPosnY()).kill(this)));
						if(chessBoard.isCheck(color)){
							position.setPosn(initial);
							return false;
						}
						
						return true;
					}else if (destination.getPosnX()== position.getPosnX()){
						if(destination.getPosnY()== position.getPosnY()+1 && color==WHITE){
							hasMoved=true;
							return false;
						}else if (destination.getPosnY()== position.getPosnY()-1 && color==BLACK){
							hasMoved=true;
							return false;
						}else 
							return false;
					}else{
						position.setPosn(destination);
						if(chessBoard.isCheck(color)){
							System.out.println("still checked");
							position.setPosn(initial);
							return false;
						}
						return true;
					}						
				}
			}else{
				position.setPosn(destination);
				if(chessBoard.isCheck(color)){
					position.setPosn(initial);
					return false;
				}
				return true; 
			}
		}else {
			return false;
		}
		
	}
	
	void promote() throws RemoteException{
		Board chessBoard= Board.getInstance(color);
		chessBoard.promote(position);
		state=INACTIVE;
		
	}

	
	public ChessPiece kill(ChessPiece a) {
		state=INACTIVE;
		killer=a;
		return null;
	}
	
	public boolean validateMove1(Location destination){
		if( Math.abs(destination.getPosnX()-position.getPosnX())==1 && destination.getPosnY()-position.getPosnY()==-1 && color == BLACK)
			return true;
		else if( Math.abs(destination.getPosnX()-position.getPosnX())==1 && destination.getPosnY()-position.getPosnY()==1 && color == WHITE)
			return true;
		else 
			return false;
	}
	
	public boolean hasCheckOnOpossingKing(Location positionOfOpposingKing) {
		if(state==INACTIVE) return false;
		if(validateMove1(positionOfOpposingKing))
			return true;
		return false;

	}

	
}
