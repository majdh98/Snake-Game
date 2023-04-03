import java.util.*;
public class snakebody {

	//Instance Variables

	protected Vector<node> snakeb;//snake body; where all the node except last node are stored
	protected node lastnode;
	protected int counter;//to decide where in snakeb the snake body start
	protected int lnd;//last node direction: 1 for east, 2 for north, 3 for west and 4 for south

	//Constructor
	public snakebody(int noderad) {
		node a= new node (0,29); //should be y max
		node b= new node (noderad,29);
		node c= new node (2*noderad, 29);
		node d= new node (3*noderad, 29);
		node e= new node (4*noderad, 29);
		node f= new node (5*noderad, 29);
		node g= new node (6*noderad, 29);
		snakeb=new Vector<node>(2);
		snakeb.add(a);
		snakeb.add(b);
		snakeb.add(c);
		snakeb.add(d);
		snakeb.add(e);
		snakeb.add(f);
		lastnode=g;
		counter=0;
		lnd=1;
	}

	//Instance Methods

	//return an empty snakebody
	public snakebody empty() {
		return null;
	}

	//get the first node
	public node getFirstNode() {
			return snakeb.get(counter);
	}

	//get the last node
	public node getLastNode() {
		return lastnode;
	}

	//return node at index i in sankeb
	public node getnode(int i) {
		return snakeb.get(i);
	}
	
	//get last node direction
	public int getLND() {
		return lnd;
	}

	//get counter
	public int getCounter() {
		return counter;
	}
	
	//set counter
	public void setCounter(int counter) {
		this.counter=counter;
	}
	
	//set last node direction
	public void setLND(int lnd) {
		this.lnd=lnd;
	}

	//set a new coordinates with x and y to node at index i
	public void setnode(int i, int x, int y) {
		snakeb.set(i, snakeb.get(i).setCoord(x,y));
	}

	//add dx and dy to the coordinates of the last node
	public void moveLastNode(int dx,int dy) {
		lastnode=new node (lastnode.getx()+dx,lastnode.gety()+dy);
	}
	
	//set the coordinate of last node to(x,y)
	public void setLastNode(int x,int y) {
		lastnode=new node (x,y);
	}
	

	//return the size of snakb
	public int size() {
		return snakeb.size();
	}

	//add a node at the end
	public void addNode(node n) {
		snakeb.add(lastnode);
		lastnode=n;
	}
	
}
