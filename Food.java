
public class Food extends node {
	
	//Instance Variables
	protected int foodtype;//Food Type: 1 for green, 2 for red food, 3 for speedy food, 4 for slowMo food
	
	//Constructor
	public Food (int x, int y, int foodtype) {
		super (x,y);				
		this.foodtype=foodtype;
	}
	
	//Instance Methods
	
	//get food type
	public int getFoodType() {
		return foodtype;
	}
	
	//set food type to foodtype
	public void setFoodType(int foodtype) {
		this.foodtype=foodtype;
	}
}
