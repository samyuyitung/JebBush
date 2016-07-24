package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
//import java.awt.Color; 
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

public class Player {
	int MAX_WIDTH;
	// variables
	public int x_pos;
	public int y_pos;
	public int xSpeed;
	public int ySpeed;
	public int level;
	public boolean jumping;
	int jumpTime;
	private int width;
	private int height;
	boolean invinsible = false;
	private boolean isDead;
	public int health = 10;
	int invinsibleCount = 0;
	private static List<Bullet> bullets = new CopyOnWriteArrayList<>();
	ImageIcon trump = new ImageIcon("trump.png");
	ImageIcon sadTrump = new ImageIcon("sadtrump.jpg");

	int facing = 1;

	// constructor
	Player(int x, int y, int width) {
		x_pos = x;
		y_pos = y;
		this.width = 80;
		height = 100;
		MAX_WIDTH = width;
		health += level;
	}

	public void move() {
		if (x_pos < 0) {
			x_pos = 0;
		} else if (x_pos > MAX_WIDTH - width) {
			x_pos = MAX_WIDTH - width;
		}
		x_pos += xSpeed;
		if (xSpeed < 0)
			facing = -1;
		else
			facing = 1;
		if (ySpeed > 0) {
			y_pos -= ySpeed;
			ySpeed = 0;
		} else if (jumpTime > 10) {
			y_pos += 50;
			jumpTime = 0;
			jumping = false;
		}
		if (jumping)
			jumpTime++;

		if (invinsible && invinsibleCount++  > 5) {
			invinsible = false;
			invinsibleCount = 0;
		}
	}

	public void setXSpeed(int speed) {
		xSpeed = speed;
	}

	public void setYSpeed(int speed) {
		ySpeed = speed;
		jumping = true;
	}

	static List<Bullet> getBullets() {
		return bullets;
	}

	public void shootABullet() {
		if (bullets.size() < 10)
			bullets.add(new Bullet(x_pos + width / 2, y_pos + height / 2, facing, 1));

	}

	public boolean getIsDead() {
		return isDead;
	}

	public void decrementHealth() {
		if (!invinsible) {
			invinsible = true;
			health--;
			if (health == 0)
				isDead = true;
		}
	}

	// draw the player
	public void drawPlayer(Graphics g, ImageObserver img) {
		if(invinsible)
			g.drawImage(sadTrump.getImage(), x_pos, y_pos, img);
			else
			
			g.drawImage(trump.getImage(), x_pos, y_pos, img);
		g.setColor(Color.red);
		g.fillRect(5, 5, health * 60, 50);
		for (Bullet b : bullets) {
			b.drawShot(g, img, b.dir);
		}
	}

	public void addHealth() {
		health += 2;
		if (health > 10)
			health = 10;

	}

}
