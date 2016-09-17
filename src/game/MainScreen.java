package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JPanel implements ActionListener{

	/**
	 * Create the panel.
	 */
	GamePanel gamePanel ;
	final ScoreBoardPanel p;
	
	public MainScreen() {
		setSize(400,700);
		
		 p=new ScoreBoardPanel();
		p.setBounds(0, 0, 400, 100);
		
		JButton level1 = new JButton("Level1");
		level1.setBounds(83, 26, 213, 83);
		level1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gamePanel = new GamePanel(p,"level1");
				gamePanel.setBounds(0, 100, 400, 600);
				MainFrame.contentPane.add(p);
				MainFrame.contentPane.add(gamePanel);
				gamePanel.requestFocus(true);
				gamePanel.requestFocusInWindow();
				MainFrame.contentPane.repaint();
				
			}
		});
		setLayout(null);
		add(level1);
		
		JButton level2 = new JButton("Level2");
		level2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gamePanel = new GamePanel(p,"level2");
				gamePanel.setBounds(0, 100, 400, 600);
				MainFrame.contentPane.add(p);
				MainFrame.contentPane.add(gamePanel);
				gamePanel.requestFocus(true);
				gamePanel.requestFocusInWindow();
				MainFrame.contentPane.repaint();
			}
		});
		level2.setBounds(89, 162, 207, 83);
		add(level2);
		
		JButton Level3 = new JButton("Level3");
		Level3.setBounds(89, 304, 207, 97);
		Level3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gamePanel = new GamePanel(p,"level3");
				gamePanel.setBounds(0, 100, 400, 600);
				MainFrame.contentPane.add(p);
				MainFrame.contentPane.add(gamePanel);
				gamePanel.requestFocus(true);
				gamePanel.requestFocusInWindow();
				MainFrame.contentPane.repaint();
			}
		});
		add(Level3);
		
		JButton Level4 = new JButton("Level4");
		Level4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gamePanel = new GamePanel(p,"level2");
				gamePanel.setBounds(0, 100, 400, 600);
				MainFrame.contentPane.add(p);
				MainFrame.contentPane.add(gamePanel);
				gamePanel.requestFocus(true);
				gamePanel.requestFocusInWindow();
				MainFrame.contentPane.repaint();
			}
		});
		
		Level4.setBounds(89, 447, 213, 97);
		Level4.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		add(Level4);
		
		
	}
	public void paintComponent(Graphics g)
	{		g.drawImage(new ImageIcon("src/img/bg.jpg").getImage(),0,0,400,700,null);
	g.setColor(Color.DARK_GRAY);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		
	}

}
