package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSensor implements ActionListener {
	int i = 0;
	Menu me;
	public ButtonSensor(Menu me) {
		this.me = me;	
	}
	@Override
	public void actionPerformed(ActionEvent ap) {
		if(ap.getActionCommand()=="Start") {
			System.out.println("Started.");
			if(i==0) {
				new StartGame();
			}
			i = 1;
		}
	}

}
