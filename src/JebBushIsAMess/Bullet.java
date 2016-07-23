package JebBushIsAMess;

import java.awt.Graphics; 
import java.awt.Color; 

public class Bullet {
	private int x_pos;
	private int y_pos;
	
	public Bullet(int x, int y) {
		x_pos = x;
		y_pos = y;
	}
	
	public void moveShot(int speed) {
		x_pos += speed;
	}
	
	public void drawShot(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x_pos, y_pos,10, 10);
	}
}
