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
	int chance= 2000000; 
	
	int condition =0; 
	
	ImageIcon hill = new ImageIcon("hillary.jpeg");
    int MAX_WIDTH; 
	
	
	
	
	
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
		
		x+=20; 
		if ((int) (Math.random()*chance) == 0)
			condition=1; 
		else 
			chance--; 
			
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
		g.setColor(Color.green);
		g.drawImage(hill.getImage(), x, y, img);
	}
	
	public void airDeath()
	{
		
		
		
		chance =15;
	}
	
	
}
