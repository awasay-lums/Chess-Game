import java.rmi.RemoteException;
import java.util.ArrayList;


public class Board implements Constants {
	/*
	 * 	A collection of the  black and white chess pieces
	 * */
	private static Board instanceB= null;
	private static Board instanceW= null;

	public static Board getInstance(int color){
		if(instanceB==null){
			instanceB= new Board();
		}
		return instanceB;
		/*if(color==BLACK){
			if(instanceB==null){
				instanceB=new Board();
				return instanceB;
			}
			return instanceB;
		}			
		else{
			if(instanceW==null){
				instanceW=new Board();
				return instanceW;
			}
			return instanceW;
		}*/
	}
	private Board(){
		WhitePieces= new ArrayList<ChessPiece>();
		BlackPieces= new ArrayList<ChessPiece>();
		initializePieces();
		initializeBoard();
		
	}
	 
		
	ChessPiece[][] chessBoard;
	ArrayList<ChessPiece> WhitePieces;
	ArrayList<ChessPiece> BlackPieces;
	
	public King BKing;
	public King WKing;
	
	public void initializePieces(){
		
		/*
		 * 	Add the chess Pawns
		 * */
		BKing = new King(5,8,BLACK);
		WKing = new King(5,1,WHITE);
		
		for (int i=0;i<8;i++){
			
			WhitePieces.add(new Pawn(i+1,2,WHITE));
			BlackPieces.add(new Pawn(i+1,7,BLACK));
			
		}
		/*
		 * King, queen, Knight, Rook, Bishop
		 * */
		WhitePieces.add(WKing);
		BlackPieces.add(BKing);
		
		WhitePieces.add(new Queen(4,1,WHITE));
		BlackPieces.add(new Queen(4,8,BLACK));
		
		WhitePieces.add(new Rook(1,1,WHITE));
		WhitePieces.add(new Rook(8,1,WHITE));
		BlackPieces.add(new Rook(1,8,BLACK));
		BlackPieces.add(new Rook(8,8,BLACK));
		
		WhitePieces.add(new Knight(2,1,WHITE));
		WhitePieces.add(new Knight(7,1,WHITE));
		BlackPieces.add(new Knight(2,8,BLACK));
		BlackPieces.add(new Knight(7,8,BLACK));
		
		WhitePieces.add(new Bishop(3,1,WHITE));
		WhitePieces.add(new Bishop(6,1,WHITE));
		BlackPieces.add(new Bishop(3,8,BLACK));
		BlackPieces.add(new Bishop(6,8,BLACK));
		
	}
	
	public void initializeBoard(){
		chessBoard=new ChessPiece[9][9];
		for (ChessPiece b: BlackPieces){
			chessBoard[b.getLocation().getPosnX()][b.getLocation().getPosnY()]= b;
		}
		for (ChessPiece w: WhitePieces){
			chessBoard[w.getLocation().getPosnX()][w.getLocation().getPosnY()]= w;
		}
	}
/*
 * 	This function checks to see if the chessBoard at a particular position is Empty;
 * */
	public boolean isEmpty(int x, int y){
		return (chessBoard[x][y]==null);
		
	}
	
/*
 * 	This function returns the color of the chessPiece at a position
 * */
	public int getColorAt(int x, int y){
		if (chessBoard[x][y]==null)
			return -1;
		if(chessBoard[x][y].isBlack())
			return BLACK;
		else 
			return WHITE;
	}
	/*
	 * 	This function Checks to see if the move is valid:
	 * */
	
	public boolean isValidMove(int inx,int iny, int finx, int finy) throws RemoteException{
			 
			if(chessBoard[inx][iny].makeMove(new Location(finx,finy))){
				
				chessBoard[finx][finy]=chessBoard[inx][iny];					
				chessBoard[inx][iny]=null;
				return true;
			
			}else {
				
				return false;
			
			}
				
			
	}
	/*
	 * Check if there is a check on a color king:
	 * */
	
	public boolean isCheck(int color){
		
	
		if(color==BLACK){
			for (int i=0;i<WhitePieces.size();i++){
				if(WhitePieces.get(i).hasCheckOnOpossingKing(new Location(BKing.getLocation()))){
							return true;
				}
			}
			return false;
		}else {
			for (int i=0;i<BlackPieces.size();i++){
				if(BlackPieces.get(i).hasCheckOnOpossingKing(new Location(WKing.getLocation()))){
						return true;
				}
			}
			return false;			
		}
		
	}
	
	public ChessPiece getPieceAt(int x, int y){
		return chessBoard[x][y];
	}
	
	public void setPieceAt(int x, int y,ChessPiece a){
		chessBoard[x][y]=a;
	}
	
	public void makeMove(int inx, int iny, int finx, int finy, boolean isClient){
		if(isClient){
			chessBoard[finx][finy]=chessBoard[inx][iny];
			chessBoard[inx][iny]=null;
			chessBoard[finx][finy].makeMove(new Location(finx, finy));
			chessBoard[finx][finy].setPosition(new Location(finx,finy));
		}
		return;
	}
	
	int promote(Location position) throws RemoteException{
		
			int color=chessBoard[position.getPosnX()][position.getPosnY()].getColor();
			if(chessBoard[position.getPosnX()][position.getPosnY()] instanceof Pawn && color== BLACK && position.getPosnY()==1){
				chessBoard[position.getPosnX()][position.getPosnY()].setState(INACTIVE);
				chessBoard[position.getPosnX()][position.getPosnY()]=new Queen(position.getPosnX(),position.getPosnY(),color);
				BlackPieces.add(chessBoard[position.getPosnX()][position.getPosnY()]);
				return color;
			}
			if(chessBoard[position.getPosnX()][position.getPosnY()] instanceof Pawn && color== WHITE && position.getPosnY()==8){
				chessBoard[position.getPosnX()][position.getPosnY()].setState(INACTIVE);
				chessBoard[position.getPosnX()][position.getPosnY()]=new Queen(position.getPosnX(),position.getPosnY(),color);
				WhitePieces.add(chessBoard[position.getPosnX()][position.getPosnY()]);
				return color;
			}
			return -1;
	}

}
