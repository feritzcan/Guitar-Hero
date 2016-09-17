package game;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class ScoreBoardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel scoreLabel,lifeLabel;
	public ScoreBoardPanel() {
		setSize(400,100);
		setLayout(null);
		
		JLabel lblScore = new JLabel("SCORE");
		lblScore.setForeground(Color.PINK);
		lblScore.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblScore.setBounds(88, 6, 75, 26);
		add(lblScore);
		
		JLabel label = new JLabel("LIFE");
		label.setForeground(Color.PINK);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		label.setBounds(232, 6, 75, 26);
		add(label);
		
		scoreLabel = new JLabel("0");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		scoreLabel.setBounds(102, 44, 61, 16);
		add(scoreLabel);
		
		lifeLabel = new JLabel("0");
		lifeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lifeLabel.setForeground(Color.WHITE);
		lifeLabel.setBounds(242, 44, 61, 16);
		add(lifeLabel);
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(new ImageIcon("src/img/scorebg.jpeg").getImage(), 0, 0,400,100, this);
	}
	
	public   void update()
	{
		scoreLabel.setText(""+GamePanel.point);
		lifeLabel.setText(""+GamePanel.life);
		repaint();
	}
}
