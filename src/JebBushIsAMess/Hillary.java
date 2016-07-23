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
	int chance= 30; 
	boolean hitGround =false; 
	
	
	int condition =0; 
	
	ImageIcon hill = new ImageIcon("hillary.jpg");
    int MAX_WIDTH; 
	int MIN_HEIGHT=550; 
	
	
	
	
	Hillary(int hx, int hy, int htype){
		x=hx; 
		y=hy;
		type=htype;
		setHeightandWidth(type); 
		
	}
	
	
	public void setHeightandWidth(int type){
		switch (type){
			case 1: 
				height=80; 
				width =60; 
				break; 
			case 2: 
				height = 70; 
				width =90; 
		}
		
		
	}
	
	public void move(){
		
		x+=10; 
		if ((int) (Math.random()*chance) == 0)
			condition=1; 
		else 
			chance--; 
			
		System.out.println("MOVEDDDD");
	}
	
	public void doSomething(){
		switch (condition){
		case 0: 
			move(); 
			break; 
		case 1: 
			airDeath(); 
			condition=0; 
			break; 
		}
		move(); 
		
	}
	
	
	
	void drawHillary(Graphics g, ImageObserver img) {
		g.drawImage(hill.getImage(), x, y, img);
	}
	
	public void airDeath()
	{  
		y+=40; 
		System.out.println(y);
		
		if (y>900){
			System.out.println("lmao");
			condition=0; 
			chance =30; 
			hitGround =true; 
		}

	}
	
	
}
