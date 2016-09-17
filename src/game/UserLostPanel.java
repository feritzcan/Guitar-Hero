package game;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLostPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	int nameX=150,nameY=200;

	public UserLostPanel() {

		setBounds(0,0,400,700);
		setLayout(null);
		
		JLabel lblYouWon = new JLabel("YOU LOST!");
		lblYouWon.setForeground(Color.PINK);
		lblYouWon.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblYouWon.setBounds(95, 82, 342, 114);
		add(lblYouWon);
		
		JButton btnMainMenu = new JButton("MAIN MENU");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				MainFrame.contentPane.add(new MainScreen());
			}
		});
		btnMainMenu.setBounds(6, 535, 181, 67);
		add(btnMainMenu);
	}
	public void paintComponent(Graphics g)
	{		g.drawImage(new ImageIcon("src/img/bg.jpg").getImage(),0,0,400,700,null);
	g.drawString("Tolga Tınaztepe", nameX, nameY);
	g.drawString("Murat Süerdem", nameX, nameY+15);
	g.drawString("Kağan Erbay", nameX, nameY+30);
	g.drawString("Ahmet Sarıgüney", nameX, nameY+45);
	g.drawString("Özgü Oral", nameX, nameY+60);
	}
}
