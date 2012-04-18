import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteGUI extends Remote{
	void makeMove(int x, int y, int z , int w, boolean a) throws RemoteException;
	void KingCheck() throws RemoteException;
	void visible() throws RemoteException;
	
	}
