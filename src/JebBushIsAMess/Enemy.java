package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

public class Enemy {
	int MAX_WIDTH;
	int x;
	int y;
	int type;
	int height;
	int width;
	boolean jumping;
	int facing = -1;
	ImageIcon ted = new ImageIcon("ted.jpeg");
	List<Bullet> bullets = new CopyOnWriteArrayList<>();

	Enemy(int ax, int atype, int width) {
		x = ax;
		setHeightAndWidth(type);
		y = 450 - height;
		type = atype;
		jumping = false;
		MAX_WIDTH = width;

	}
	
	void setHeightAndWidth(int type) {
		switch (type) {
		case 1:
			height = 80;
			width = 60;
			break;
		}
	}

	void move() {
		if (jumping) {
			y += 100;
			jumping = false;
		} else {
			int dir = (int) (Math.random() * 3); // Up = 0; Left = 1; Right =
													// true

			if (dir == 0) {
				y -= 100;
				jumping = true;
			} else if (dir == 1 && x > 20) {
				x -= 20;
			} else if (dir == 2 && x < MAX_WIDTH - width - 20) {
				x += 20;
			}
		}
	}

	void setFacing(int player_x) {
		if (player_x > x)
			facing = 1;
		else
			facing = -1;
	}
	List<Bullet> getBullets(){
		return bullets;
	}
	void drawEnemy(Graphics g, ImageObserver img) {
		g.setColor(Color.green);
		g.drawImage(ted.getImage(), x, y, img);
		for (Bullet b : bullets)
			b.drawShot(g, img, 2);
	}

	boolean checkBulletHit() {
		return false;
	}

	void shoot() {
		if (bullets.size() < 1)
			bullets.add(new Bullet(x, y, facing, 2));

	}

	boolean doSomething(int playerx, int playery) {
		setFacing(playerx);
		int thing = (int) (Math.random() * 5);
		if (thing == 1)
			move();
		if (thing == 0)
			shoot();
		for (Bullet b : bullets) {
			if (b.checkHit(playerx, playery, 80,100)){
				bullets.remove(b);
				System.out.println("hit");
				return true;
				
			}
			else if (b.fly(800))
				bullets.remove(b);
		}
		return false;
	}

}
