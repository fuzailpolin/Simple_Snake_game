package text;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import paint.Paint;

public class Text extends JComponent implements Paint {
	String text;
	public Text(String text) {
		this.text = text;
		//this.Score = String.valueOf(Score);
	}
	public Text(int Score) {
		this.text = String.valueOf(Score);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Times new Roman", Font.BOLD , 40));
		g2d.drawString(this.text, 5,50);
		
	}
}
