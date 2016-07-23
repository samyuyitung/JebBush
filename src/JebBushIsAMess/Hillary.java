package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Hillary {

	int height;
	int width;
	int x;
	int y;
	int type;
	int chance = 30;
	boolean hitGround = false;

	boolean isDead;

	int condition = 0;
	int deathCount = 0;
	ImageIcon hill = new ImageIcon("hillary.jpg");
	ImageIcon boom = new ImageIcon("boom.jpeg");
	int MAX_WIDTH;
	int MIN_HEIGHT = 550;

	Hillary(int hx, int hy, int htype) {
		reset(hx, hy, htype);
	}

	void reset(int hx, int hy, int htype) {
		x = hx;
		y = hy;
		type = htype;
		setHeightandWidth(type);
		hitGround = false;
		deathCount = 0;
	}

	public void setHeightandWidth(int type) {
		switch (type) {
		case 1:
			height = 80;
			width = 60;
			break;
		case 2:
			height = 70;
			width = 90;
		}

	}

	public void move() {

		x += 10;
		if ((int) (Math.random() * chance) == 0)
			condition = 1;
		else
			chance--;
	}

	public void doSomething() {
		switch (condition) {
		case 0:
			move();
			break;
		case 1:
			airDeath();
			condition = 0;
			break;
		}
		move();

	}

	void drawHillary(Graphics g, ImageObserver img) {
		if (!isDead)
			if (hitGround) {
				g.drawImage(boom.getImage(), x, 500, img);
			} else
				g.drawImage(hill.getImage(), x, y, img);
	}

	public void airDeath() {
		y += 40;

		if (y > 700) {
			condition = 0;
			chance = 30;
			hitGround = true;
		}

	}

	void goBoom() {
		System.out.println(deathCount);
		if (deathCount++ > 10)
			reset(0, 70, 0);
	}

	public boolean checkHit(int obj_x, int obj_y, int obj_w, int obj_h) {
		// above or below
		if (x <= obj_x + obj_w && // far right
				x + width >= obj_x && // far left
				y <= obj_y + obj_h && // bottom
				y + height >= obj_y) // top
			return true;
		return false;
	}

}
