package JebBushIsAMess;

import java.awt.Graphics; 
import java.awt.Color; 

public class Bullet {
	private int x_pos;
	private int y_pos;
	private int dir;
	private int speed = 10;
	
	public Bullet(int x, int y, int dir) {
		x_pos = x;
		y_pos = y;
		this.dir = dir;
	}
	
	public void moveShot(int speed) {
		x_pos += speed;
	}
	
	public void drawShot(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x_pos, y_pos,10, 10);
	}
	
	public boolean checkPlayerHit(int obj_x, int obj_y){
		//above or below
		if(y_pos + 5 > obj_y || y_pos - 5 < obj_y + 50)
			return false;
		//if between player
		if(x_pos - 5 <= obj_x + 50 && x_pos + 5 > obj_x - 5)
			return true;
		return false;
	}	
	
	boolean fly(int MAX_WIDTH){
		x_pos += speed * dir;

		if(x_pos < 0 || x_pos > MAX_WIDTH)
			return true;
		else return false;
	}
}
