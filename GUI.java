import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
public class GUI extends UnicastRemoteObject implements MouseListener,Constants, RemoteGUI{
	/*
	 * 	The class is declared as singleton
	 * */
	static GUI guiB=null;
	static GUI guiW=null;
	boolean checked;
	Color inB, inW;
	Location inK;
		
	
	public static GUI getInstance(int color) throws RemoteException{
		if(color==BLACK)
			if(guiB==null)
				return guiB= new GUI(BLACK);
			else 
				return guiB;
		else if(color==WHITE)
			if(guiW==null)
				return guiW= new GUI(WHITE);
			else 
				return guiW;
		else
			return null;
	}
	private GUI(int color) throws RemoteException{
		mycolor=color;
	}
	int mycolor;
	int colorInPlay;
	
	//private static final GUI gui= new GUI();
	
	

	boolean isClient=false;
	
	Board chessBoard= Board.getInstance(mycolor);
	
	
	JFrame frame;
	JPanel panel;
	JPanel main;
	JPanel first;
	JPanel second;
	boolean selected;
	JLabel tempFirst=null;
	int inx=0;
	int iny=0;
	Location kingLoc;
	JPanel kingPan;
		
	
	int a=1;
	

	
	public void makeBoard() {
		
		
		/*
		 * 	Initialize the frames and the panels
		 * */
		frame=new JFrame("Chess Game");
		panel= new JPanel(new GridLayout(8,8));
		//panel.setPreferredSize(new Dimension(800,700));
		
		int n=0;int a=1;int b=0;
		
		/*
		 * Create the checkered board using alternating colors
		 * */
		
		
		for (int i=0;i<64;i++){
			JPanel label= new JPanel(new GridLayout(1,1));
			label.add(new JLabel(new ImageIcon()));
			label.addMouseListener(this);
			//label.add(new JLabel("chess" + n));
			
			if(i%2==a)
				label.setBackground(Color.cyan);
			if(i%2==b)
				label.setBackground(Color.white);
			
			if(i%8==7){
				int temp=a;
				a=b;
				b=temp;				
			}			
			panel.add(label, n);
			n++;
		}
		/*
		 * 	Set the images at the proper location
		 * */
		((JPanel) panel.getComponent(0)).remove(0);
		((JPanel) panel.getComponent(0)).add(new JLabel(new ImageIcon("rookB.png")));
		((JPanel) panel.getComponent(1)).remove(0);
		((JPanel) panel.getComponent(1)).add(new JLabel(new ImageIcon("horseB.png")));
		((JPanel) panel.getComponent(2)).remove(0);
		((JPanel) panel.getComponent(2)).add(new JLabel(new ImageIcon("bishopB.png")));
		((JPanel) panel.getComponent(4)).remove(0);
		((JPanel) panel.getComponent(4)).add(new JLabel(new ImageIcon("queenB.png")));
		((JPanel) panel.getComponent(3)).remove(0);
		((JPanel) panel.getComponent(3)).add(new JLabel(new ImageIcon("kingB.png")));
		((JPanel) panel.getComponent(5)).remove(0);
		((JPanel) panel.getComponent(5)).add(new JLabel(new ImageIcon("bishopB.png")));
		((JPanel) panel.getComponent(6)).remove(0);
		((JPanel) panel.getComponent(6)).add(new JLabel(new ImageIcon("horseB.png")));
		((JPanel) panel.getComponent(7)).remove(0);
		((JPanel) panel.getComponent(7)).add(new JLabel(new ImageIcon("rookB.png")));
		((JPanel) panel.getComponent(8)).remove(0);
		((JPanel) panel.getComponent(8)).add(new JLabel(new ImageIcon("pawnB.png")));
		((JPanel) panel.getComponent(9)).remove(0);
		((JPanel) panel.getComponent(9)).add(new JLabel(new ImageIcon("pawnB.png")));
		((JPanel) panel.getComponent(10)).remove(0);
		((JPanel) panel.getComponent(10)).add(new JLabel(new ImageIcon("pawnB.png")));
		((JPanel) panel.getComponent(11)).remove(0);
		((JPanel) panel.getComponent(11)).add(new JLabel(new ImageIcon("pawnB.png")));
		((JPanel) panel.getComponent(12)).remove(0);
		((JPanel) panel.getComponent(12)).add(new JLabel(new ImageIcon("pawnB.png")));
		((JPanel) panel.getComponent(13)).remove(0);
		((JPanel) panel.getComponent(13)).add(new JLabel(new ImageIcon("pawnB.png")));
		((JPanel) panel.getComponent(14)).remove(0);
		((JPanel) panel.getComponent(14)).add(new JLabel(new ImageIcon("pawnB.png")));
		((JPanel) panel.getComponent(15)).remove(0);
		((JPanel) panel.getComponent(15)).add(new JLabel(new ImageIcon("pawnB.png")));
		
		((JPanel) panel.getComponent(63)).remove(0);
		((JPanel) panel.getComponent(63)).add(new JLabel(new ImageIcon("rookW.png")));
		((JPanel) panel.getComponent(62)).remove(0);
		((JPanel) panel.getComponent(62)).add(new JLabel(new ImageIcon("horseW.png")));
		((JPanel) panel.getComponent(61)).remove(0);
		((JPanel) panel.getComponent(61)).add(new JLabel(new ImageIcon("bishopW.png")));
		((JPanel) panel.getComponent(59)).remove(0);
		((JPanel) panel.getComponent(59)).add(new JLabel(new ImageIcon("kingW.png")));
		((JPanel) panel.getComponent(60)).remove(0);
		((JPanel) panel.getComponent(60)).add(new JLabel(new ImageIcon("queenW.png")));
		((JPanel) panel.getComponent(58)).remove(0);
		((JPanel) panel.getComponent(58)).add(new JLabel(new ImageIcon("bishopW.png")));
		((JPanel) panel.getComponent(57)).remove(0);
		((JPanel) panel.getComponent(57)).add(new JLabel(new ImageIcon("horseW.png")));
		((JPanel) panel.getComponent(56)).remove(0);
		((JPanel) panel.getComponent(56)).add(new JLabel(new ImageIcon("rookW.png")));
		((JPanel) panel.getComponent(55)).remove(0);
		((JPanel) panel.getComponent(55)).add(new JLabel(new ImageIcon("pawnW.png")));
		((JPanel) panel.getComponent(54)).remove(0);
		((JPanel) panel.getComponent(54)).add(new JLabel(new ImageIcon("pawnW.png")));
		((JPanel) panel.getComponent(53)).remove(0);
		((JPanel) panel.getComponent(53)).add(new JLabel(new ImageIcon("pawnW.png")));
		((JPanel) panel.getComponent(52)).remove(0);
		((JPanel) panel.getComponent(52)).add(new JLabel(new ImageIcon("pawnW.png")));
		((JPanel) panel.getComponent(51)).remove(0);
		((JPanel) panel.getComponent(51)).add(new JLabel(new ImageIcon("pawnW.png")));
		((JPanel) panel.getComponent(50)).remove(0);
		((JPanel) panel.getComponent(50)).add(new JLabel(new ImageIcon("pawnW.png")));
		((JPanel) panel.getComponent(49)).remove(0);
		((JPanel) panel.getComponent(49)).add(new JLabel(new ImageIcon("pawnW.png")));
		((JPanel) panel.getComponent(48)).remove(0);
		((JPanel) panel.getComponent(48)).add(new JLabel(new ImageIcon("pawnW.png")));
		
		/*
		 * 		Add a mouse Listener:
		 * */
		
		frame.addMouseListener(this);
		
		/*
		 * 	Initialize the frame:
		 * */
		
		frame.add(panel);
		frame.setSize(600, 600);
		frame.getContentPane().setPreferredSize(new Dimension(800,700));
		
		
		
		/*
		 * 	Set the color of the starting side:
		 * */
		colorInPlay=WHITE;
		
	}
	
	
	



	@Override
	public void mousePressed(MouseEvent e){
		if(chessBoard.isCheck(mycolor)){
			frame.setName("Checked");			
		}
		try{
			int x=e.getComponent().getX()/e.getComponent().getWidth()+1;
			int y=7-e.getComponent().getY()/e.getComponent().getHeight()+1;		
		
		if (!selected){
			if(chessBoard.getColorAt(x,y)==colorInPlay && !chessBoard.isEmpty(x, y) && colorInPlay==mycolor){
			
				((JPanel) e.getComponent()).setBorder(new LineBorder(Color.DARK_GRAY,5));
				((JPanel) e.getComponent()).setBorder(new LineBorder(Color.DARK_GRAY,5));
				inx=e.getComponent().getX()/e.getComponent().getWidth()+1;
				iny=7-e.getComponent().getY()/e.getComponent().getHeight()+1;
				
				first=(JPanel)e.getComponent();
				
				selected=true;
			}			
		}else {
			int finx=e.getComponent().getX()/e.getComponent().getWidth()+1;
			int finy=7-e.getComponent().getY()/e.getComponent().getHeight()+1;
			
			if (chessBoard.isValidMove(inx,iny, finx, finy) ){
				
				selected=false;
				first.setBorder(new LineBorder(Color.DARK_GRAY,0));
				/*
				 * 	This piece of code implements the On screen move functionality
				 * */
				makeMove(inx,iny,finx,finy,false);
				if(mycolor==WHITE ){
					ChessServer.getInstance().getServer1().makeMove(inx,iny,finx,finy,true);
				}else if (mycolor==BLACK ) {
					ChessServer.getInstance().getServer2().makeMove(inx,iny,finx,finy,true);
				}
				
			}else{
				selected=false;
				first.setBorder(new LineBorder(Color.DARK_GRAY,0));
								
			}
		}

		} catch (Exception ex){
			ex.printStackTrace();
			
			
		}
		
		
	}


	
	
	public void promote(int xa,int ya,int type){
		int c=xa+ (8-ya)*8 -1;
		JPanel a=((JPanel) panel.getComponent(0));
		JPanel b=((JPanel) panel.getComponent(0));
		

		JLabel d=((JLabel) a.getComponent(0));
		d.setIcon(new ImageIcon("pawnB.png"));
		JLabel f=((JLabel) b.getComponent(0));
		f.setIcon(new ImageIcon("pawnB.png"));
	}
	public void makeMove(int inx, int iny, int finx, int finy,boolean isRemote) throws RemoteException{
		
	
		
		JPanel initial=(JPanel) panel.getComponent( (inx + (8-iny)*8)-1);
		JLabel in=(JLabel)initial.getComponent(0);
		JPanel fin=(JPanel) panel.getComponent( (finx + (8-finy)*8)-1);
		JLabel finL=(JLabel)fin.getComponent(0);
		finL.setIcon(in.getIcon());
		in.setIcon(null);
		if(!isRemote){
			int out=chessBoard.promote(new Location(finx,finy));
			if(out!=-1){
				if(out==BLACK){
					int c=finx+ (8-finy)*8 -1;
					JPanel a=((JPanel) panel.getComponent(c));
					JLabel d=((JLabel) a.getComponent(0));
					d.setIcon(new ImageIcon("kingB.png"));
				}else if (out==WHITE){
					int c=finx+ (8-finy)*8 -1;
					JPanel a=((JPanel) panel.getComponent(c));
					JLabel d=((JLabel) a.getComponent(0));
					d.setIcon(new ImageIcon("kingW.png"));
				}
			}
		}
			//KingCheck();
			if(colorInPlay==BLACK)
				colorInPlay=WHITE;
			else
				colorInPlay=BLACK;
			
			if(isRemote){
				chessBoard.makeMove(inx, iny, finx, finy,isRemote);
				int out=chessBoard.promote(new Location(finx,finy));
				if(out!=-1){
					if(out==BLACK){
						int c=finx+ (8-finy)*8 -1;
						JPanel a=((JPanel) panel.getComponent(c));
						JLabel d=((JLabel) a.getComponent(0));
						d.setIcon(new ImageIcon("kingB.png"));
					}else if (out==WHITE){
						int c=finx+ (8-finy)*8 -1;
						JPanel a=((JPanel) panel.getComponent(c));
						JLabel d=((JLabel) a.getComponent(0));
						d.setIcon(new ImageIcon("kingW.png"));
						
					}
				}
			}
			KingCheck();
			if(mycolor==WHITE ){
				ChessServer.getInstance().getServer1().KingCheck();
			}else if (mycolor==BLACK ) {
				ChessServer.getInstance().getServer2().KingCheck();
			}
	}
	
		@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void KingCheck(){
		boolean done =false;
		
		if(chessBoard.isCheck(BLACK)){
					
			int x=chessBoard.BKing.getLocation().getPosnX();
			int y=chessBoard.BKing.getLocation().getPosnY();
			inK=new Location(x,y);
			JPanel a=((JPanel) panel.getComponent(x+ (8-y)*8 -1));
			Color c=a.getBackground();
			System.out.println("HEY");
			a.setBackground(Color.red);
			if(inB==null){
				inB=c;
				done =true;
			}		
					
		}else if(!(chessBoard.isCheck(BLACK)) ) {
			if(inK==null){
				inK=chessBoard.BKing.getLocation();
			}
			
			int x=inK.getPosnX();
			int y=inK.getPosnY();
			JPanel a=((JPanel) panel.getComponent(x+ (8-y)*8 -1));
			if(inB==null){
				inB=a.getBackground();
				}
			a.setBackground(inB);
			inB=null;			
			done=true;
				
		}
		
		if(chessBoard.isCheck(WHITE)){
			
			int x=chessBoard.WKing.getLocation().getPosnX();
			int y=chessBoard.WKing.getLocation().getPosnY();
			inK=new Location(x,y);
			JPanel a=((JPanel) panel.getComponent(x+ (8-y)*8 -1));
			Color c=a.getBackground();
			a.setBackground(Color.red);
			if(inW==null){
				inW=c;
			}		
					
		}else if(!chessBoard.isCheck(WHITE)){
			if(inK==null){
				inK=chessBoard.WKing.getLocation();
			}
			
			int x=inK.getPosnX();
			int y=inK.getPosnY();
			JPanel a=((JPanel) panel.getComponent(x+ (8-y)*8 -1));
			if(inW==null){
				inW=a.getBackground();
				}
			a.setBackground(inW);
			inW=null;			
				
		
		}
	}
	
	public void visible(){
		frame.setVisible(true);
	}
}
