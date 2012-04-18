import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BoardManager extends Remote {
	
	public void initializePieces() throws RemoteException;
	
	public void initializeBoard() throws RemoteException;
	
	public boolean isEmpty(int x, int y) throws RemoteException;
	
	public int getColorAt(int x, int y) throws RemoteException;
	
	public boolean isValidMove(int inx,int iny, int finx, int finy) throws RemoteException;
	
	public boolean isCheck(int color) throws RemoteException;
	
	public ChessPiece getPieceAt(int x, int y) throws RemoteException;
	
	public void setPieceAt(int x, int y,ChessPiece a) throws RemoteException;
}
