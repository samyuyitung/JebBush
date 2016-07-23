package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
//import java.awt.Color; 
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {
	// variables
	public int x_pos;
	private int y_pos;
	public int xSpeed;
	public int ySpeed;
	public boolean jumping;
	int jumpTime;
	private int width;
	private int height;

	private boolean isDead;
	private int health = 10;
	private static List<Bullet> bullets = new CopyOnWriteArrayList<>();

	int facing = 1;

	// constructor
	Player(int x, int y) {
		x_pos = x;
		y_pos = y;
		width = 100;
		height = 100;
	}
	public void move(){
		x_pos += xSpeed;
		if (xSpeed < 0)
			facing = -1;
		else
			facing = 1;
		if(ySpeed > 0){
		y_pos -= ySpeed;
		ySpeed = 0;
		} else if(jumpTime > 10){
			y_pos += 20;
			jumpTime = 0;
			jumping = false;
		}			
		if(jumping)
			jumpTime ++;
	}
	
	public void setXSpeed(int speed) {
		xSpeed = speed;
	}

	public void setYSpeed(int speed) {
		ySpeed = speed;
		jumping = true;
	}
	
	static List<Bullet> getBullets(){
		return bullets;
	}

	public void shootABullet() {
		if (bullets.size() < 10)
			bullets.add(new Bullet(x_pos + width / 2 , y_pos  + height / 2, facing));

	}

	public boolean getIsDead() {
		return isDead;
	}

	// draw the player
	public void drawPlayer(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x_pos, y_pos, width, height);

		for (Bullet b : bullets) {
			b.drawShot(g);
		}
	}

}
