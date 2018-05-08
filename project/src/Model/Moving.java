package Model;

public class Moving extends GameObject implements Directable {

	private int speed;
	private int life;
	int direction = EAST;  
	
	public Moving(int X, int Y, int color, int speed, int life) {
		super(X, Y, color);
		this.speed = speed;
		this.life = life;
	}
	 
	public void move(int X, int Y) {
	        this.posX = this.posX + X;
	        this.posY = this.posY + Y;
    }

    public void rotate(int x, int y) {
        if(x == 0 && y == -1)
            direction = NORTH;
        else if(x == 0 && y == 1)
            direction = SOUTH;
        else if(x == 1 && y == 0)
            direction = EAST;
        else if(x == -1 && y == 0)
            direction = WEST;
    }
    
    public void loseLife(int damage) {
    	this.life -= damage;
    }
    public int getDirection() {
        return direction;
    }

    public int getFrontX() {
        int delta = 0;
        if (direction % 2 == 0){
            delta += 1 - direction;
        }
        return this.posX + delta;
    }

    public int getFrontY() {
        int delta = 0;
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return this.posY + delta;
    }
	@Override
	public boolean isObstacle() {
		return true;
	}

}
