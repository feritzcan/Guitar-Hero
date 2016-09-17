package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javazoom.jl.player.*;

import javax.swing.JLabel;

import java.io.*;

import sun.audio.*;
public class GamePanel extends JPanel implements KeyListener,Runnable{

	/**
	 * Create the panel.
	 */
	
	//MEDİA
	Timer myTimer;
	Player playMP3;
	Thread music;
	//AudioInputStream missSound;
	
	AudioPlayer player;
	static int point;
	static int life;
	long time=0,start;
	static int speed=3;
	static int radius;
	int width=MainFrame.width;
	int height=MainFrame.height;
	static int slots[];
	int nameX=0,nameY=0;
	ArrayList<Integer> slotData0=new ArrayList<Integer>();
	ArrayList<Integer> slotData1=new ArrayList<Integer>();
	ArrayList<Integer> slotData2=new ArrayList<Integer>();
	ArrayList<Integer> slotData3=new ArrayList<Integer>();
	ArrayList<Integer> slotData4=new ArrayList<Integer>();
	ArrayList<Integer> slotData5=new ArrayList<Integer>();
	ArrayList<Integer> slotData6=new ArrayList<Integer>();
	ArrayList<Circle> slot0=new ArrayList<Circle>();
	ArrayList<Circle> slot1=new ArrayList<Circle>();
	ArrayList<Circle> slot2=new ArrayList<Circle>();
	ArrayList<Circle> slot3=new ArrayList<Circle>();
	ArrayList<Circle> slot4=new ArrayList<Circle>();
	ArrayList<Circle> slot5=new ArrayList<Circle>();
	ArrayList<Circle> slot6=new ArrayList<Circle>();

	boolean send[]={false,false,false,false,false,false,false};

	ScoreBoardPanel scorep;
 String level;
	
 	boolean s1=false,s2=false,s3=false,s4=false,s5=false,s6=false;
	boolean enable[]={true,false,false,false,false,false,false};
	Hole holes[]=new Hole[6];
	public GamePanel(ScoreBoardPanel p,String level)  {
		this.scorep=p;
		life=20;
		this.level=level;
        loadData();
        player=new AudioPlayer();
		point=0;
		 start= TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		slots=new int[6];
		music=new Thread(this);
		music.start();
		addKeyListener(this);
		setFocusable(true);
          setSize(MainFrame.width,MainFrame.height);
          setLayout(null);
        
          int edge=10*7;// Yuvarlakların aralıkları
          radius=(width-edge)/12;
          int fazlalik=8;
          int x=12;
          for(int a=0;a<6;a++)
          {
        	  slots[a]=x+12;
        	  holes[a]=new Hole(x, height-height/6);
        	  x=x+10+2*radius;
          }
          update();
          
          requestFocusInWindow();
          

         
	}
	
	
	
	public void paintComponent(Graphics g)
	{		g.drawImage(new ImageIcon("src/img/bg.jpg").getImage(),0,0,width,height,null);

		g.setColor(Color.DARK_GRAY);
		g.drawString("Ferit Özcan", nameX, nameY);
		

	
		g.setColor(Color.CYAN);
		for(int a=0;a<6;a++)
		{
			g.drawImage(holes[a].getImage(), holes[a].x, holes[a].y, radius*2, radius*2, this);

		}
		 for(int a=0;a<slot0.size();a++)
    	 {
			g.drawImage(slot0.get(a).getImage(),slot0.get(a).x,slot0.get(a).y,slot0.get(a).radius, slot0.get(a).radius,this);
    	 }
		 for(int a=0;a<slot1.size();a++)
    	 {
			g.drawImage(slot1.get(a).getImage(),slot1.get(a).x,slot1.get(a).y,slot1.get(a).radius, slot1.get(a).radius,this);
    	 }
		 for(int a=0;a<slot2.size();a++)
    	 {
			g.drawImage(slot2.get(a).getImage(),slot2.get(a).x,slot2.get(a).y,slot2.get(a).radius, slot2.get(a).radius,this);
    	 }
		 for(int a=0;a<slot3.size();a++)
	   	 {
				g.drawImage(slot3.get(a).getImage(),slot3.get(a).x,slot3.get(a).y,slot3.get(a).radius, slot3.get(a).radius,this);
	   	 }
		 for(int a=0;a<slot4.size();a++)
	   	 {
				g.drawImage(slot4.get(a).getImage(),slot4.get(a).x,slot4.get(a).y,slot4.get(a).radius, slot4.get(a).radius,this);
	   	 }
		 for(int a=0;a<slot5.size();a++)
	   	 {
				g.drawImage(slot5.get(a).getImage(),slot5.get(a).x,slot5.get(a).y,slot5.get(a).radius, slot5.get(a).radius,this);
	   	 }
		 
	}
	
	public void loadData()
	{
		Scanner fileScan=null;
		try {
			fileScan = new Scanner(new File("src/data/"+level+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner strScan;
		int slot=0;
		while(fileScan.hasNext())
		{
			String line=fileScan.nextLine();
			strScan=new Scanner(line);
			strScan.useDelimiter(" ");
			while(strScan.hasNext())
			{
				int sec=Integer.parseInt(strScan.next());
				if(slot==0)slotData0.add(sec);
				if(slot==1)slotData1.add(sec);
				if(slot==2)slotData2.add(sec);
				if(slot==3)slotData3.add(sec);
				if(slot==4)slotData4.add(sec);
				if(slot==5)slotData5.add(sec);
				if(slot==6)slotData6.add(sec);
			}
			slot++;
		}
	}
	
	public void isOver()
	{
		if(slotData0.size()==0&&slotData1.size()==0&&slotData2.size()==0&&slotData3.size()==0&&slotData4.size()==0&&slotData5.size()==0&&slotData6.size()==0&&life!=0)
		{
			myTimer.cancel();
			music.stop();
			playMP3.close();
			setVisible(false);
			scorep.setVisible(false);
			MainFrame.contentPane.add(new UserWonPanel(point));
			MainFrame.contentPane.repaint();
		}
		if(life<=0)
		{
			myTimer.cancel();
			music.stop();
			playMP3.close();
			setVisible(false);
			scorep.setVisible(false);
			MainFrame.contentPane.add(new UserLostPanel());
			MainFrame.contentPane.repaint();
		}
		
	}
	public void update()
	{
		   myTimer=new Timer();
		  
          TimerTask gorev =new TimerTask() {

                 @Override
                 public void run() {
                          isOver();
                	 if(time!=TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())-start)
                	 {
                		 time=TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())-start;
                		 for(int a=0;a<7;a++)
                		 {
                			 send[a]=false;
                		 }
                	 }
                	 
                	 for(int a=0;a<slot0.size();a++)
                	 {
                		 slot0.get(a).update();
                		 if(slot0.get(a).y>holes[0].y+2*radius)
                			 {
                			 	life--;
                			 slot0.remove(a);
                			 }
                	 }
                	 for(int a=0;a<slot1.size();a++)
                	 {
                		 slot1.get(a).update();
                		 if(slot1.get(a).y>holes[0].y+2*radius)
                			 {
                			 life--;
                			 slot1.remove(a);
                			 }

                	 }
                	 for(int a=0;a<slot2.size();a++)
                	 {
                		 slot2.get(a).update();
                		 if(slot2.get(a).y>holes[0].y+2*radius){
                			 life--;
                			 slot2.remove(a);
                		 }

                	 }
                	 for(int a=0;a<slot3.size();a++)
                	 {
                		 slot3.get(a).update();
                		 if(slot3.get(a).y>holes[0].y+2*radius){
                			 life--;
                			 slot3.remove(a);
                		 }

                	 }
                	 for(int a=0;a<slot4.size();a++)
                	 {
                		 slot4.get(a).update();
                		 if(slot4.get(a).y<-100||slot4.get(a).y>holes[0].y+2*radius){
                			 life--;
                			 slot4.remove(a);
                		 }

                	 }
                	 for(int a=0;a<slot5.size();a++)
                	 {
                		 slot5.get(a).update();
                		 if(slot5.get(a).y>holes[0].y+2*radius){
                			 life--;
                			 slot5.remove(a);
                		 }

                	 }
                	 for(int a=0;a<slot6.size();a++)
                	 {
                		 slot6.get(a).update();
                		 if(slot6.get(a).y>holes[0].y+2*radius){
                			 life--;
                			 slot6.remove(a);
                		 }

                	 }
                	
                	 scorep.update();
                	 if(nameX<400)
                	 {
                		 nameX+=4;
                	 }
                	 else
                	 {
                		 nameX=-100;
                		 nameY=nameY+65;
                	 }
                	 if(nameY>height)
                	 {
                		 nameY=-80;
                		 nameX=-60;
                	 }
                	 repaint();
                	handleCircle();
                	 
                	 
                	 
                	 //  myTimer.cancel();
                 }
          };
          
          myTimer.schedule(gorev,0,17);

	}
	
	public void handleCircle()
	{
		if(send[0]==false&&slotData0.size()>0&&time==slotData0.get(0))
		{
			slotData0.remove(0);
			slot0.add(new Circle(0));
			send[0]=true;
		}
		if(send[1]==false&&slotData1.size()>0&&time==slotData1.get(0))
		{
			slotData1.remove(0);

			slot1.add(new Circle(1));
			send[1]=true;
			
		}if(send[2]==false&&slotData2.size()>0&&time==slotData2.get(0))
		{
			slotData2.remove(0);

			slot2.add(new Circle(2));
			send[2]=true;
		}if(send[3]==false&&slotData3.size()>0&&time==slotData3.get(0))
		{
			slotData3.remove(0);

			slot3.add(new Circle(3));
			send[3]=true;
			
		}if(send[4]==false&&slotData4.size()>0&&time==slotData4.get(0))
		{
			slotData4.remove(0);

			slot4.add(new Circle(4));
			send[4]=true;
			
		}if(send[5]==false&&slotData5.size()>0&&time==slotData5.get(0))
		{
			slotData5.remove(0);

			slot5.add(new Circle(5));
			send[5]=true;
			
		}if(send[6]==false&&slotData6.size()>0&&time==slotData6.get(0))
		{
			slotData6.remove(0);

			slot6.add(new Circle(6));
			send[6]=true;
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public boolean checkHit(int slot)
	{
		Circle c=null;
		Hole h=null;
		ArrayList<Circle> circ=null;
		if(slot==0&&slot0.size()>0)
		{
				c=slot0.get(0);
				circ=slot0;
				h=holes[0];
		}
		if(slot==1&&slot1.size()>0)
			{
			c=slot1.get(0);
			circ=slot1;
			h=holes[1];
			}
		if(slot==2&&slot2.size()>0){
			h=holes[2];
			circ=slot2;
			c=slot2.get(0);
		}
		if(slot==3&&slot3.size()>0){
			h=holes[3];
			circ=slot3;
			c=slot3.get(0);
		}
		if(slot==4&&slot4.size()>0){
			h=holes[4];
			c=slot4.get(0);
			circ=slot4;
		}
		if(slot==5&&slot5.size()>0){
			h=holes[5];
			c=slot5.get(0);
			circ=slot5;
		}
		if(slot==6&&slot6.size()>0){
			h=holes[6];
			c=slot6.get(0);
			circ=slot6;
		}
		if(c==null||h==null)
		{
			player.play("src/mp3/bass_guitar1.wav");

			return false;
			
		}
		int ust=c.y;
		int alt=c.y+radius;
		int holeUst=h.y;
		int holeAlt=h.y+2*radius;
		
		System.out.println();
		if(ust>=holeUst-3&&holeAlt+3>=alt)
		{
			point+=5;
			
			return true;
		}
		else
		{
			player.play("src/mp3/bass_guitar1.wav");

			return false;
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		s1=true;
		System.out.println("key");
		if(e.getKeyChar()=='a')
		{
			s1=true;
			holes[0].press=true;
			if(checkHit(0))
			{
				slot0.get(0).gone=true;
				slot0.add(slot0.get(0));
				slot0.remove(0);

			}
			repaint();
		}
		if(e.getKeyChar()=='s')
		{
			holes[1].press=true;
			if(checkHit(1))
			{
				slot1.get(0).gone=true;
				slot1.add(slot1.get(0));
				slot1.remove(0);

			}
			repaint();

		}
		if(e.getKeyChar()=='d')
		{
			holes[2].press=true;

			if(checkHit(2))
			{
				slot2.get(0).gone=true;
				slot2.add(slot2.get(0));
				slot2.remove(0);

			}
			repaint();

		}
		if(e.getKeyChar()=='j')
		{
			holes[3].press=true;

			if(checkHit(3))
			{
				slot3.get(0).gone=true;
				slot3.add(slot3.get(0));
				slot3.remove(0);

			}
			repaint();

		}
		if(e.getKeyChar()=='k')
		{
			holes[4].press=true;

			if(checkHit(4))
			{
				slot4.get(0).gone=true;
				slot4.add(slot4.get(0));
				slot4.remove(0);

			}
			repaint();

		}
		if(e.getKeyChar()=='l')
		{
			holes[5].press=true;

			if(checkHit(0))
			{
				slot5.get(0).gone=true;
				slot5.add(slot5.get(0));
				slot5.remove(0);

			}
			repaint();

		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyChar()=='a')
		{
			holes[0].press=false;
			repaint();

		}
		if(e.getKeyChar()=='s')
		{
			holes[1].press=false;
			repaint();

		}
		if(e.getKeyChar()=='d')
		{
			holes[2].press=false;
			repaint();

		}
		if(e.getKeyChar()=='j')
		{
			holes[3].press=false;
			repaint();

		}
		if(e.getKeyChar()=='k')
		{
			holes[4].press=false;
			repaint();

		}
		if(e.getKeyChar()=='l')
		{
			holes[5].press=false;
			repaint();

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{

		    FileInputStream fis = new FileInputStream("src/mp3/"+level+".mp3");
		     playMP3 = new Player(fis);

		    playMP3.play();
		    
		    

		    }catch(Exception e){System.out.println(e);}
				
	}
}
