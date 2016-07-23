package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics; 
//import java.awt.Color; 

public class Player { 
	// variables 
	private int x_pos; 
	private int y_pos; 
	public boolean jumping;
	private int width;
	private int height;
	
	private boolean isDead;
	private int health = 10;
	
	
	// constructor 
	Player(int x, int y)  { 
		x_pos = x; 
		y_pos = y; 
		width = 100;
		height = 100;
	} 
	
	public void moveX(int speed) { 
		x_pos += speed; 
	} 

	public void moveY(int speed) {
		y_pos += speed;
	}

	public boolean getIsDead(){
		return isDead;
	}
	// draw the player 
	public void drawPlayer(Graphics g) { 
		  g.setColor(Color.blue);
		  g.fillRect(x_pos, y_pos, width, height);
	} 
	
	
	
	
} 
