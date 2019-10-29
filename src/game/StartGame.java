package game;
import java.awt.Dimension;

import javax.swing.JFrame;

public class StartGame {
	public StartGame() {
		JFrame GameFrame = new JFrame("Hungry Snake");
		GameFrame.setContentPane(new Snake());
		GameFrame.pack();
		GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame.setPreferredSize(new Dimension(Snake.Width, Snake.Height));
		//obj.setLayout(null);
		GameFrame.setResizable(false);
		GameFrame.setVisible(true);
	}
}
