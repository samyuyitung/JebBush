package JebBushIsAMess;

import java.awt.Graphics; 
import java.awt.Color; 
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class finalBosos {
	int x;
	int y;
	int height;
	int width;
	int facing = -1;
	boolean jumping;
	List<Bullet> bullets = new CopyOnWriteArrayList<>();
	ImageIcon img = new ImageIcon("jeb-bush.jpg");
	
	
	finalBosos(int xPos, int yPos) {
		int size = (int) (Math.random() *5) + 5;
		int type = (int) (Math.random() *5) + 5;
		
		height = size * type;
		width = size * type;
		
		x = xPos;
		y = yPos;
		
	}
	
	void move() {
		if (jumping) {
			y += 100;
			jumping = false;
		} else {
			int dir = (int) (Math.random() * 6); // Up = 0; Left = 1; Right =
													// true

			if (dir == 0) {
				y -= 100;
				jumping = true;
			} else if (dir == 1) {
				x -= 30;
			} else if (dir == 2) {
				x += 30;
			}
		}
		int size = (int) (Math.random() *5) + 5;
		int type = (int) (Math.random() *5) + 5;
		
		height = size * type;
		width = size * type;
		
	}
	
	void setFacing(int player_x){
		if(player_x > x)
			facing = 1;
		else
			facing = -1;
	}
	boolean checkBulletHit() {
		return false;
	}
	
	void shoot() {
		if (bullets.size() < 10) {
			bullets.add(new Bullet(x, y, facing, 2));
			bullets.add(new Bullet(x,y,-facing, 2));
		}
		
	}
	
	void drawBoss(Graphics g, ImageObserver image) {
		g.drawImage(img.getImage(), x, y, height, width, image);
		for (Bullet b : bullets)
			b.drawShot(g, image, 2);
	}
	
	void doSomething(int playerx) {
		setFacing(playerx);
		int thing = (int) (Math.random() * 2);
		if (thing == 1)
			move();
		if (thing == 0)
			shoot();
		for (Bullet b : bullets) {
			if (b.fly(800))
				bullets.remove(b);
		}
	}

}
