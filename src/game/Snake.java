package game;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

//import java.awt.TextField;

public class Snake extends JPanel implements ActionListener,Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static File scoreFile = new File("Highscore.txt");
	
	public static final int Width = 500;
	public static final int Height = 500;
	private final int dots = 10; //height and the width of the images. not ready yet so 1.
	private final int Max_Dots = (Width*Height)/(dots*dots); //max position Snake and the Food can traverse.
	
	private int SnakeBody=3;
	private final int SnakeX[] = new int[Max_Dots];
	private final int SnakeY[] = new int[Max_Dots]; //position of the body of the snake..
	private int X, Y;
	private  int SnakeHeadX;
	private  int SnakeHeadY;
	private int EnemyX;
	private int EnemyY;
	
	private static int Score=0;
	private String Highscore = "";
	Timer tm;
	private int Keypressed=0;
	
	Random rand1 = new Random();
	Random rand2 = new Random();
	Random rand3 = new Random();
	Random rand4 = new Random();
	
	private  int FoodX=0;
	private  int FoodY=0; //position of the food.. should be random.. so define it later.
	
	private boolean inGame=true;
	
	public Snake(){
		setPreferredSize(new Dimension(Width, Height));
		setFPS(100);
		tm.start();
		Init();
		setFocusable(true); 
		requestFocus();
		addKeyListener(this);
		//CreateBody();
		paint(null);
		
	}
	public void Init() {
		int  x, y;
		
		inGame = true;
		SnakeHeadX=Width/2;
		SnakeHeadY=Height/2;
		
		FoodX=rand1.nextInt(500)+1;
		FoodY=rand2.nextInt(500)+1;
		EnemyX = rand3.nextInt(500)+1;
		EnemyY = rand4.nextInt(500)+1;
		
		x = (FoodX%10);
		y = (FoodY%10);
		FoodX = FoodX-x;
		FoodY = FoodY-y;
		
		x = (EnemyX%10);
		y = (EnemyY%10);
		EnemyX = EnemyX-x;
		EnemyY = EnemyY-y;
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		DoDrawing(g);
	}
	
	private void DoDrawing(Graphics g) {
		int i;
		//TextField t1=new TextField("Game Over");
		if(inGame == true) {
			g.drawString("Score: "+Score, 10, 10);
			g.drawString("Highscore: "+Highscore, 100, 10);
			g.setColor(Color.ORANGE);
			g.fillOval(FoodX, FoodY, dots, dots);
			g.setColor(Color.RED);
			g.fillRect(EnemyX, EnemyY, dots, dots);
			g.setColor(Color.BLUE);
			g.fillOval(SnakeHeadX, SnakeHeadY, dots, dots);//will change the i with the snakeX[], and Y axis with the snakeY[];
			for(i=1; i<=SnakeBody; i++) {
				g.fillOval(SnakeX[i], SnakeY[i], dots, dots);
			}
		}
		//else add(t1);
		if(Highscore.equals("")) {
			this.Highscore = getHighscore();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c==KeyEvent.VK_DOWN) {
			X = 0;
			Y = 10;
			System.out.println("Down");
		}
		else if(c==KeyEvent.VK_UP) {
			X = 0;
			Y = -10;
			System.out.println("Up");
		}
		else if(c==KeyEvent.VK_LEFT) {
			X = -10;
			Y = 0;
			System.out.println("Left");
		}
		else if(c==KeyEvent.VK_RIGHT) {
			X = 10;
			Y = 0;
			System.out.println("Right");
		}
		Keypressed = 1;
		//https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html
	}
	public void actionPerformed(ActionEvent e) {
				update();
				CreateBody();
				if(Keypressed==1) CheckCollision();
				repaint();
		
		
	}
	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		
	}
	private void update() {
		int x, y;
		if(SnakeHeadX>500) SnakeHeadX = 0;
		else if(SnakeHeadX<0) SnakeHeadX = 500;
		else if(SnakeHeadY>500) SnakeHeadY = 0;
		else if(SnakeHeadY<0) SnakeHeadY = 500;
		
		SnakeHeadX+=X;
		SnakeHeadY+=Y;
		if(SnakeHeadX==FoodX && SnakeHeadY==FoodY) {
			FoodX=rand1.nextInt(500)+1;
			FoodY=rand2.nextInt(500)+1;
			x = (FoodX%10);
			y = (FoodY%10);
			FoodX = FoodX-x;
			FoodY = FoodY-y;
			SnakeBody++;
			Score+=10;
			System.out.println(Score);
		}
		if(SnakeHeadX==EnemyX && SnakeHeadY==EnemyY) {
			EnemyX=rand1.nextInt(500)+1;
			EnemyY=rand2.nextInt(500)+1;
			x = (EnemyX%10);
			y = (EnemyY%10);
			EnemyX = EnemyX-x;
			EnemyY = EnemyY-y;
			if(SnakeBody>=4)SnakeBody--;
			if(Score>=10)Score-=10;
			System.out.println(Score);
		}
		
	}
	private void CreateBody() {
		int i, tailprevX1, tailprevX2, tailprevY1, tailprevY2;
		SnakeX[0] = SnakeHeadX;
		SnakeY[0] = SnakeHeadY;
		tailprevX1 = SnakeX[0];
		tailprevY1 = SnakeY[0];
		for(i=1; i<=SnakeBody; i++) {
			tailprevX2 = SnakeX[i];
			tailprevY2 = SnakeY[i];
			SnakeX[i] = tailprevX1;
			SnakeY[i] = tailprevY1;
			tailprevX1 = tailprevX2;
			tailprevY1 = tailprevY2;
			//System.out.println(SnakeX[i]+" "+SnakeY[i]);
		}
	}
	
	public String getHighscore() {
		BufferedReader reader = null;
		FileReader readfile = null;
		try{
			readfile = new FileReader("Highscore.txt");
			reader = new BufferedReader(readfile);
			return reader.readLine();
		}
		catch(Exception e){
			return "Noone :0";
		}
		finally {
			try{
				if(reader!=null)
					reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void CheckHighscore() {
		if(Score>Integer.parseInt((Highscore.split(":")[1]))) {
			String Name = JOptionPane.showInputDialog("New Highscore. YOur Name: ");
			this.Highscore = Name + " :" +Score;
		}
		
		
		if(!scoreFile.exists()) {
			try {
				scoreFile.createNewFile();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		FileWriter writeFile = null;
		BufferedWriter writer = null;
		try {
			writeFile = new FileWriter(scoreFile);
			writer = new BufferedWriter(writeFile);
			writer.write(this.Highscore);
		}
		catch(Exception e) {
			
		}
		finally {
			try{
				if(writer!=null)
					writer.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void setFPS(int x) {
		tm = new Timer(x, this);
	}
	private void CheckCollision() {
		int i;
		for(i=2; i<SnakeBody; i++) {
			if(SnakeHeadX == SnakeX[i] && SnakeHeadY == SnakeY[i]) {
				System.out.println("False ");
				inGame = false;
				CheckHighscore();
			}
	
		}
		
	}


}
