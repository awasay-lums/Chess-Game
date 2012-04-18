
public class Location {
	/*
	 * The variables of position on the board
	 * */
	
	public Location(int x, int y){
		posnX=x;
		posnY=y;
	}
	public Location(Location x){
		posnX=x.getPosnX();
		posnY=x.getPosnY();
	}
	public Location(){}
	
	private int posnX;
	private int posnY;
	
	/*
	 *	The getter and the setter functions 
	 * */
	
	int getPosnX(){
		
		return posnX;
	}
	
	int getPosnY(){
		
		return posnY;
	}
	
	void setPosn(int x,int y){
		
		posnX=x;
		posnY=y;
	}
	
	void setPosn(Location naya){
		posnX=naya.getPosnX();
		posnY=naya.getPosnY();
	}
	
	public String toString(){
		
		return(posnX+" "+posnY);
		
	}
	
}
