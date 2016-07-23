package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
//import java.awt.Color; 
import java.util.List;

public class Player { 
	// variables 
	private int x_pos; 
	private int y_pos; 
	public boolean jumping;
	private int width;
	private int height;
	
	private boolean isDead;
	private int health = 10;
	private List<Bullet> bullets = new ArrayList<>();
	
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

	public void shootABullet(){
		if(bullets.size() < 10)
			bullets.add(new Bullet(x_pos,y_pos));
		
	}
	
	
	public boolean getIsDead(){
		return isDead;
	}
	// draw the player 
	public void drawPlayer(Graphics g) { 
		  g.setColor(Color.blue);
		  g.fillRect(x_pos, y_pos, width, height);
	
		  for(Bullet b : bullets){
			  b.drawShot(g);
		  }
	}
	
	
} 
