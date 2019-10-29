package game;
import javax.swing.JButton;
import javax.swing.JFrame;

import text.Text;

public class Menu extends JFrame {
	public Menu() {
		JFrame menu = new JFrame("Menu");
		Text text = new Text("Snake Game");
		menu.pack();
		menu.setBounds(0,0,500,500);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton start = new JButton("Start");
		start.setBounds(200, 220, 100, 50);
		text.setBounds(150, 50, 250, 250);
		menu.add(start);
		menu.add(text);
		menu.setLayout(null);
		//obj.setLayout(null);
		menu.setResizable(false);
		menu.setVisible(true);
		ButtonSensor bs = new ButtonSensor(this);
		start.addActionListener(bs);
		
	}
}
