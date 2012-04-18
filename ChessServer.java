import java.io.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ChessServer implements Constants{
	
	public static ChessServer server = new ChessServer();
	/*
	 * The getter of the class as a singleton
	 * */
	public static ChessServer getInstance(){
		return server;
	}
	static String IP;
	
	
	public void setUpServer1(){
		
		if (System.getSecurityManager() == null) {
       // System.setSecurityManager(new SecurityManager());
		 }
		

		 try{
			GUI gui= GUI.getInstance(WHITE);
			gui.makeBoard();
			RemoteGUI remote1=(RemoteGUI)gui;
		 	//RemoteGUI stub1=(RemoteGUI) UnicastRemoteObject.exportObject(remote1,0);
		 	Registry registry = LocateRegistry.getRegistry();
		 	registry.rebind("Remote1", remote1);
		 	System.out.println("server started, waiting for a client to join...");			
		} catch (Exception ex){
		ex.printStackTrace();
}
	}
	public void setUpServer2(){
		 if (System.getSecurityManager() == null) {
		 //       System.setSecurityManager(new SecurityManager());
				 }
				

				 try{
					 System.out.println(IP);
					GUI gui= GUI.getInstance(BLACK);
					gui.makeBoard();
					RemoteGUI remote2=(RemoteGUI)gui;
					Registry registry = LocateRegistry.getRegistry();
				 	registry.rebind("Remote2", remote2);
				 	System.out.println("server started");	
				 	if(getServer2()==null){
				 		System.out.println("wrong IP");
				 	}
				 	getServer2().visible();
				 	gui.visible();
				 	
				} catch (Exception ex){
				System.out.println("Some error caused the program to exit");
		}
	}
	
	public RemoteGUI getServer1(){
			 if (System.getSecurityManager() == null) {
			//	             System.setSecurityManager(new SecurityManager());
		          }
			 try{
				 
				 Registry registry = LocateRegistry.getRegistry(IP);
				 RemoteGUI gui= (RemoteGUI) registry.lookup("Remote2");
				 return gui;
			 }catch (Exception e){
				System.out.println("The host IP is invalid");
			 }
			 return null;
		}
	
	public RemoteGUI getServer2(){
		 if (System.getSecurityManager() == null) {
			  //          System.setSecurityManager(new SecurityManager());
	          }
		 try{
			
			 RemoteGUI gui= (RemoteGUI) Naming.lookup("Remote1");
			 return gui;
		 }catch (Exception e){
			 
		 }
		 return null;
}
	

	
	public void setUpClient(){

	}
	
	public static void takeIP(){
		
	}
	
	public static void main(String[] args){
		
		System.out.println("Welcome to chess :, please select from the following menu: \n 1. Start a game.\n 2. Join a game." );
		String in=System.console().readLine();
		int a=Integer.parseInt(in);
		
		if(a==1){
			ChessServer.getInstance().setUpServer1();			
		}else if(a==2){
			Console console= System.console();
			IP=console.readLine("Please Input the IP of the host: \n  -->");
			ChessServer.getInstance().setUpServer2();
		}
		
		//ChessServer.getInstance().setUpServer2();
		//ChessServer.getInstance().getServer1();
		
		
	}
}
