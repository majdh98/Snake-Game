
public class node {
	//Instance Variables
	protected int x;//x coordinate
	protected int y;//y coordinate
	

	
	//Constructor	
	public node (int x,int y) {
		this.x=x;
		this.y=y;
	}

	//Instance Methods
	
	//return x coordinate of node
	public int getx() {
		return x;
	}
	
	//return y coordinates of node
	public int gety() {
		return y;
	}
	
	
	//set x and y coordinate of node
	public static node setCoord(int x, int y) {
		return new node (x,y);
	}
	
	//return an empty node
	public static node empty() {
		return null;
	}
}
