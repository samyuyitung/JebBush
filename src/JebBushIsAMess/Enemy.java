package JebBushIsAMess;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy{

	
	int x;
	int y;
	int type;
	int height;
	int width;
	boolean jumping;
	int facing = 0;
	
	Enemy(int ax, int atype){
		x = ax;
		setHeightAndWidth(type);
		y = 550 - height;
		type = atype;
		jumping = false;
	}
	
	void setHeightAndWidth(int type){
		switch(type){
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
	
	void move(){
		int dir = (int) Math.random() * 3; //Up = 0; Left = 1; Right = true
		if(jumping)
			y -= 10;
		
		else if(dir == 0){
			y += 10;
			jumping = true;
		}
		else if(dir == 1){
			x -= 10;
			facing = -1;
		}
		else if(dir == 2){
			x += 10;
			facing = 1;
		}	
	}
	
	void drawEnemy(Graphics g){
        g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
	
	boolean checkBulletHit(){
		
	}
	
	
}
