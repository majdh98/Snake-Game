import java.applet.Applet;
import java.applet.Applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JOptionPane;

public class snakeApplet extends Applet implements KeyListener, Runnable {

	//Instance Variables
	
	protected static snakeCanvas sC;//show the snake on a grid
	protected static Thread t;
	protected static int speedofmovement=120;//the pause duration of the animation
	protected static long starttimefortimer;//the start time for the timer to change 
											//the coordinates of Foods and walls(when timer=timetocahnge) 	
	protected int timetochange=10;//the duration for changing the coordinates of Foods and walls
	
	
	//Instance Methods
	
	public void init() {
		
		setLayout(new BorderLayout());
		sC= new snakeCanvas();
		sC.setBackground(Color.black);
		sC.addKeyListener(this);
		add("Center",sC);
		
	}
	

	
	public void start() {
		
		t=new Thread(this);
		t.start();
		starttimefortimer = System.currentTimeMillis(); //record start time to reset timer
		
	}

	public void stop() {
		
		t=null;
	}
	
	//called when thread is running
	public void run() {
		
		Thread currentThread=Thread.currentThread();
		while (currentThread == t) {
			double timer = (System.currentTimeMillis() - starttimefortimer) / 1000.0;
			if ((int)(timer/timetochange)==1) {//each timeofcahnge seconds, change the 
											   //coordinates of foods and walls
				sC.npc=new Vector<node>();
				sC.generateCoordinate();
				sC.fillGridArray();
			
			}
			if (sC.snake.getLND()==1) {//moving right
				if(sC.snake.getLastNode().getx()==29) {//when at the right end of the grid
					sC.moveSnake(-29, 0);					
				} else {
					sC.moveSnake(1, 0);
					
				}				
			}else if (sC.snake.getLND()==2) {//moving up
				if(sC.snake.getLastNode().gety()==0) {//when at the top end of the grid
					sC.moveSnake(0, 29);
				} else {
					sC.moveSnake(0, -1);
				}

			}else if (sC.snake.getLND()==3) {//moving left
				if(sC.snake.getLastNode().getx()==0) {//when at the left end of the grid
					sC.moveSnake(29, 0);
				} else {
					sC.moveSnake(-1, 0);
				}

			}else if (sC.snake.getLND()==4) {//moving down
				if(sC.snake.getLastNode().gety()==29) {//when at the buttom end of the grid
					sC.moveSnake(0, -29);
				} else {
					sC.moveSnake(0, 1);
				}	
			}
			try {
				Thread.sleep(speedofmovement);//wait speedofmovement milliseconds
			} catch (InterruptedException e) {
				//do nothing
			} 
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub

		int keyCode = e.getKeyCode();     
		
		//depending on the the key pressed, changing the last node direction of the snake
		if (keyCode == KeyEvent.VK_RIGHT) {//right arrow
			if (sC.snake.getLND()!=3) {
				sC.snake.setLND(1);				
			}
		} else if (keyCode == KeyEvent.VK_UP) {//up arrow
			if (sC.snake.getLND()!=4) {
				sC.snake.setLND(2);
			}
		}else if (keyCode == KeyEvent.VK_LEFT ) {//left arrow
			if (sC.snake.getLND()!=1) {
				sC.snake.setLND(3);
			}
		}else if (keyCode == KeyEvent.VK_DOWN) {//down arrow
			if (sC.snake.getLND()!=2) {
				sC.snake.setLND(4);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}








class snakeCanvas extends Canvas {

	//Instance Variable
	
	protected static int rad=1; //the radius on the grid; each 1 rad =20 pixel
	protected static int actualrad=20;//the real radius in terms of pixels
	protected snakebody snake=new snakebody(1);//create a new snake

	protected Food f= new Food (0,0,1);//this is used to instantiate the food 
									   //array with basic value so we don't get null exception
									   //when algorithm start as food array only get filled when
									   //paint is executed.
	
	protected Food[] foodarray=new Food[] {f,f,f,f};//an array to store the food elements
	protected int[][] grid=new int[30][30];//a two dimensional array to store the grid
	protected node n=new node(0,0);//used to give initial values to walls array element 
								   //so we don't get null exception when algorithm start

	protected node[] walls=new node[] {n,n,n};//an array to store the walls in the game
	protected Vector<node> npc=new Vector<node>();//non_permiting_coordinate//an array to store 
												  //the coordinate of all foods and walls
												  //so they dont overlap
	protected boolean ifrestart=true;//to check if the game was restarted or not
	
	
	//Instance Methods
	
	
	//draw the game canvas
	public void paint(Graphics g) {
		
		if (ifrestart) {//if game was restarted, generate new Coordinates for
						//foods and walls
			generateCoordinate();
			ifrestart=false;
		}

		fillGridArray();//fill the grid with correct values
		
		//paint food
		for (int i=0;i<foodarray.length;i++) {
			if(foodarray[i].getFoodType()==1)
				g.setColor(Color.green);
			if(foodarray[i].getFoodType()==2)
				g.setColor(Color.red);
			if(foodarray[i].getFoodType()==3)
				g.setColor(Color.magenta);
			if(foodarray[i].getFoodType()==4)
				g.setColor(Color.yellow);
			g.fillOval(foodarray[i].getx()*actualrad, foodarray[i].gety()*actualrad,  rad*actualrad, rad*actualrad);
		}
		
		//paint walls
		g.setColor(Color.orange);
		for (int i=0;i<walls.length;i++) {
			g.fillRect(walls[i].getx()*actualrad, walls[i].gety()*actualrad, rad*actualrad, rad*actualrad);
			
		}
		
		//paint snake
		g.setColor(Color.blue);
		for (int i=snake.getCounter();i<snake.size();i++) {
			g.fillOval((snake.getnode(i).getx())*actualrad, (snake.getnode(i).gety())*actualrad,  rad*actualrad, rad*actualrad);	
		}
		g.setColor(Color.GRAY);
		g.fillOval((snake.getLastNode().getx())*actualrad, (snake.getLastNode().gety())*actualrad,  rad*actualrad, rad*actualrad);
		
	}
	

	
	//generate new coordinates for foods and walls
	public void generateCoordinate() {
		
		generateCoordinateHelper(29);//fill npc;
		snakeApplet.starttimefortimer=System.currentTimeMillis();//to reset timer 
		int a= snake.size()-snake.getCounter()+1;//to get where in npc should the
												 //algorithm start to fill food
												 //and walls new coordinates
		
		//fill food array with new coordinate
		foodarray[0]=new Food (npc.get(a).getx(), npc.get(a).gety(),1);
		foodarray[1]= new Food (npc.get(a+1).getx(),npc.get(a+1).gety(),2);
		foodarray[2]=new Food(npc.get(a+2).getx(),npc.get(a+2).gety(),3);
		foodarray[3]=new Food(npc.get(a+3).getx(),npc.get(a+3).gety(),4);
		
		//fill walls array with new coordiantes
		walls[0]=new node (npc.get(a+4).getx(),npc.get(a+4).gety());
		walls[1]=new node (npc.get(a+5).getx(),npc.get(a+5).gety());
		walls[2]=new node (npc.get(a+6).getx(),npc.get(a+6).gety());
		
	}
	
	

	//fill npc with random number that does not overlap
	public void generateCoordinateHelper(int max) {
		
		Random r=new Random();
		int x;
		int y;
		boolean doesnotexist=true;//to check if the x and y does not exist in npc
		
		//add the coordinates of snake body into npc
		for (int i=snake.getCounter();i<snake.size();i++) {
			npc.add(snake.getnode(i));
		}
		npc.add(snake.getLastNode());
		//add more coordinates into npc for foods and walls
		while(npc.size()!=((snake.size()-snake.getCounter())+8)) {
			x=r.nextInt(max);
			y=r.nextInt(max);
			//check if the new coordinates does not exist in npc
			for (int i=0;i<npc.size();i++) {
				if (x==npc.get(i).getx()) {
					doesnotexist=false;
				}
				if(y==npc.get(i).gety()) {
					doesnotexist=false;
				}
			}
			if (doesnotexist) {
				npc.add(new node (x,y));
			}
			doesnotexist=true;
		}
		
	}
	
	

	//generate new green food when eaten by the snake
	public void generateNewGreen() {
		
		boolean doesexist=true;//to check if the new coordinate exist in npc
		Random r=new Random();
		int x=r.nextInt(29);
		int y=r.nextInt(29);
		while(doesexist) {//loop untill you find coordinates the does not exist in npc
			x=r.nextInt(29);
			y=r.nextInt(29);
			for (int i=0;i<npc.size();i++) {
				if ((x==npc.get(i).getx()) || (y==npc.get(i).gety())) {
					break;					
				}
				if (i==npc.size()-1)
					doesexist=false;
			} 			
		}
		foodarray[0]=new Food(x,y,1 );//make new green food
		
	}
	
	

	//fill the grid array
	public void fillGridArray() {
		
		grid=new int[30][30];//create new grid
		
		//fill where the snake body is with 5
		for (int i=snake.getCounter();i<snake.size();i++) {
			grid[snake.getnode(i).getx()][snake.getnode(i).gety()]=5;
		}
		grid[snake.getLastNode().getx()][snake.getLastNode().gety()]=5;
		
		//fill where the foods are with corresponding values
		for (int i=0;i<foodarray.length;i++) {
			if (foodarray[i].getFoodType()==1)
				grid[foodarray[i].getx()][foodarray[i].gety()]=1;
			if (foodarray[i].getFoodType()==2)
				grid[foodarray[i].getx()][foodarray[i].gety()]=2;
			if (foodarray[i].getFoodType()==3)
				grid[foodarray[i].getx()][foodarray[i].gety()]=3;
			if (foodarray[i].getFoodType()==4)
				grid[foodarray[i].getx()][foodarray[i].gety()]=4;
		}
		
		//fill where walls are with 6
		for (int i=0;i<walls.length;i++) {
			grid[walls[i].getx()][walls[i].gety()]=6;
		}	
		
	}
	
	
	
	//get where the next location of the red food
	public Food nextRedCoordinate(Food f) {
		
		if (f.getx()==29)//when food at the right end, move back to left end of grid
			return new Food(0,f.gety(),f.getFoodType());
		return new Food(f.getx()+1,f.gety(),f.getFoodType());//otherwise move one to the right
		
	}
	
	//to move red food along the x-axis
	//if red food does not touch the snake keep moving, otherwise
	//stop the game
	public boolean moveRedFood() {
		
		Food f=foodarray[1];//get the red food from foodarray
		if (grid[nextRedCoordinate(f).getx()][nextRedCoordinate(f).gety()]==5) {
			//if red food touched the snake, restart game and stop moving the snake
			restart();
			return false;
		}//otherwise move red food 1 to the right
		foodarray[1]=nextRedCoordinate(f);
		grid[foodarray[1].getx()][foodarray[1].gety()]=2;
		grid[f.getx()][f.gety()]=0;//reset where the food was on the grid to 0
		return true;
		
	}

	
	//print the grid for testing
	public void paintgrid() {
		
		for (int i=0;i<30;i++) {
			for (int j=0;j<30;j++) {
				System.out.print(grid[j][i]+" ");
			}
			System.out.println();
			
		}
	}
	
	//restart the game when lost
	public void restart() {
		
		JOptionPane.showMessageDialog(null, "You Lost! Your score is:"+(snake.size()-6-snake.getCounter()));
		snake=new snakebody(1);//reset snake body
		grid=new int[30][30];//reset grid
		npc=new Vector<node>();//reset npc
		generateCoordinate();//generate new coordinates for foods and walls
		fillGridArray();//fill the grid
		snakeApplet.speedofmovement=120;//reset the speed of the snake
		repaint();
	
	}
	
	//move snake
	public void moveSnake(int dx, int dy) {		
		
		if (moveRedFood()) {//check if red food hit snake, if not countinue moving snake
			node n= new node (snake.getLastNode().getx()+dx,snake.getLastNode().gety()+dy);//the location of next snake node	
			if (grid[n.getx()][n.gety()]==1) {//add 1 node to snake in case it ate green food
				snake.addNode(n);
				generateNewGreen();//generate new green food
				repaint();
				return;
			} else if (grid[n.getx()][n.gety()]==2 ||grid[n.getx()][n.gety()]==5 ||grid[n.getx()][n.gety()]==6) {
				//restart game if snake hit a wall or red food or itself
				restart();
				return;
			} else if (grid[n.getx()][n.gety()]==3) {
				//increase the speed of snake if it eats purple food
				if(snakeApplet.speedofmovement>30)
					snakeApplet.speedofmovement-=30;
				foodarray[2]=new Food (-2,0,0);//remove purple food from grid until timetochange time
			} else if (grid[n.getx()][n.gety()]==4) {
				//decrease the speed of snake if it eats Yellow food
				if (snakeApplet.speedofmovement<180)
					snakeApplet.speedofmovement+=20;
				foodarray[3]=new Food (-2,0,0);//remove Yellow foor from grid until timetochange time
			} 
			//otherwise move the snake 1 node in the direction of movement
			//by addint one node to snakeb and increasing the counter
			snake.addNode(n);
			grid[snake.getFirstNode().getx()][snake.getFirstNode().gety()]=0;
			snake.setCounter(snake.getCounter()+1);	
			repaint();
			
		}
	}
}
