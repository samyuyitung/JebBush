package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Enemy {

	int x;
	int y;
	int type;
	int height;
	int width;
	boolean jumping;
	int facing = -1;

	List<Bullet> bullets = new CopyOnWriteArrayList<>();

	Enemy(int ax, int atype) {
		x = ax;
		setHeightAndWidth(type);
		y = 550 - height;
		type = atype;
		jumping = false;
	}

	void setHeightAndWidth(int type) {
		switch (type) {
		case 1:
			height = 50;
			width = 50;
			break;
		default:
			height = 50;
			width = 50;
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
			} else if (dir == 1) {
				x -= 20;
			} else if (dir == 2) {
				x += 20;
			}
		}	}
	
	void setFacing(int player_x){
	if(player_x > x)
		facing = 1;
	else
		facing = -1;
}

	void drawEnemy(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		for (Bullet b : bullets)
			b.drawShot(g);
	}

	boolean checkBulletHit() {
		return false;
	}

	void shoot() {
		if (bullets.size() < 2) 
			bullets.add(new Bullet(x, y, facing));
		
	}

	boolean doSomething(int playerx, int playery) {
		setFacing(playerx);
		int thing = (int) (Math.random() * 2);
		if (thing == 1)
			move();
		if (thing == 0)
			shoot();
		for (Bullet b : bullets) {
			if(b.checkPlayerHit(playerx, playery))
				return true;
			else if (b.fly(800))
				bullets.remove(b);
		}
		return false;
	}

}
