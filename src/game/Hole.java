package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Hole {
	int radius=GamePanel.radius;
	int x;
	Image img,pressed;
	int y;
	boolean press;

	
	public Hole(int x,int y)
	{
		press=false;
		this.x=x;
		this.y=y;
		img=new ImageIcon("src/img/hole.png").getImage();
		pressed=new ImageIcon("src/img/hole_shine.png").getImage();

	}
	
	public Image getImage()
	{
		if(press==false)
		return img;
		else
			return pressed;
	}
	
	
}
