package game;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Circle  {
	Animation anim;
	int x;
	int y;
	int radius=GamePanel.radius;
	int slot;
	boolean gone;
	int goneSpeed=8;
	public Circle(int slot)
	{
		gone=false;
	this.x=GamePanel.slots[slot];
	this.y=-2*radius;
	this.slot=slot;
	anim=new Animation();
	anim.addFrame(new ImageIcon("src/img/hole1.png").getImage(), 100);
	anim.addFrame(new ImageIcon("src/img/hole2.png").getImage(), 100);
	anim.addFrame(new ImageIcon("src/img/hole3.png").getImage(), 100);
	anim.addFrame(new ImageIcon("src/img/hole5.png").getImage(), 100);
	

	}
	
	public void update()
	{
		if(!gone)
		this.y+=GamePanel.speed;
		else
		{
			y-=goneSpeed;
			if(slot>2)
			{
				x+=goneSpeed;
			}
			else
			{
				x-=goneSpeed;
			}
		}
		anim.update(32);

	}
	public Image getImage()
	{
		return anim.getImage();
		
	}

}
