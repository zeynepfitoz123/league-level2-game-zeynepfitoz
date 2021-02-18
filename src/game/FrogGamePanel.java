package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FrogGamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int INSTRUCTION = 3;
	int currentState = MENU;
	Font titleFont;
	Font startFont;
	Font instructionFont;
	Color swampGreen = new Color(46, 66, 29);
	Color instructionYellow = new Color(255, 229, 158);
	Color instructionText = new Color(156, 156, 109);
	Color menuGreen = new Color(128, 176, 132);
	Timer frameDraw;
	FrogCharacter frog;
	FrogObjectManager objectmanager;
	Timer addFly;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {
				System.out.println("not found");

			}
			needImage = false;
		}
	}
	
	FrogGamePanel(){
		titleFont = new Font("Monospaced", Font.PLAIN, 68);
		startFont = new Font("Monospaced", Font.PLAIN, 30);
		instructionFont = new Font("Monospaced", Font.PLAIN, 68);
		frameDraw = new Timer(1000/60, this);
	
		frameDraw.start();
		
		
		if (needImage) {
			loadImage("swamp.png");
		}
		
	}
	
	
	
	void startGame() {
		frog = new FrogCharacter(355, 550, 90, 90);
		objectmanager = new FrogObjectManager(frog);
		System.out.println("start game");
		addFly= new Timer(1000, objectmanager);
		addFly.start();
	}
	
	void updateMenuState(){
	
	}
	
	void updateInstructionState(){
		
	}
	
	void updateGameState(){
		objectmanager.update();
		if(objectmanager.gameOver) {
			currentState = END;
		}
	}
	
	void updateEndState(){
		
	}
	
	void drawMenuState(Graphics g){
		g.setColor(menuGreen);
		g.fillRect(0, 0, FrogGame.WIDTH, FrogGame.HEIGHT);
		g.setFont(titleFont);
		g.setColor(swampGreen);
		g.drawString("Fly Catchers", 142, 130);
		g.setFont(startFont);
		g.setColor(swampGreen);
		g.drawString("Press ENTER To Start", 212,400);
		g.setFont(startFont);
		g.setColor(swampGreen);
		g.drawString("Press SPACE For Instructions", 145, 570);
	}
	
	void drawInstructionState(Graphics g) {
		g.setColor(instructionYellow);
		g.fillRect(0, 0, FrogGame.WIDTH, FrogGame.HEIGHT);
		g.setColor(instructionText);
		g.setFont(instructionFont);
		g.drawString("Instructions", 142, 130);
		g.setFont(startFont);
		g.drawString("Press UP to shoot tongue at the flies.", 58, 240);
		g.drawString("Only catch flies that are the same ", 90, 290);
		g.drawString("color as the frog or you LOSE.", 112, 340);
		g.drawString("GOODLUCK :)", 295, 390);
		g.drawString("Press ENTER To Get To Menu", 150, 530);
	}
	 
	
	void drawGameState(Graphics g){
		if (gotImage) {
			g.drawImage(image, 0, 0, FrogGame.WIDTH, FrogGame.HEIGHT, null);
		} else {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, FrogGame.WIDTH, FrogGame.HEIGHT);
		
		}
		objectmanager.draw(g);
	}
	
	void drawEndState(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0, 0, FrogGame.WIDTH, FrogGame.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("You Lost", 220, 130);
		g.setFont(startFont);
		g.drawString("never fear you can always try again", 130, 200);
		g.drawString("press enter to get back to the main menu", 55, 270);
	}
	

	@Override
public void paintComponent(Graphics g) {
	if(currentState == MENU) {
		drawMenuState(g);
	}
	else if(currentState == GAME) {
		drawGameState(g);
	}
	else if(currentState == END) {
		drawEndState(g);
	}
	else if(currentState == INSTRUCTION) {
		drawInstructionState(g);
	}
	
}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU) {
			updateMenuState();
		}
		else if(currentState == GAME) {
			updateGameState();
		}
		else if(currentState == END) {
			updateEndState();
		}
		else if(currentState == INSTRUCTION) {
			updateInstructionState();
		}
		//System.out.println("action");
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT&& currentState == GAME) {
			System.out.println("LEFT");
			
			if (frog.x > 0) {
				frog.left();
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT&& currentState == GAME) {
			System.out.println("RIGHT");
			
			if (frog.x < FrogGame.WIDTH - 50) {
				frog.right();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState == END) {
				currentState = MENU;
			}
			else if(currentState == INSTRUCTION) {
				currentState = MENU;
			}
			
			else if(currentState==MENU){
				currentState=GAME;
				startGame();
			}
			
			System.out.println("current state "+ currentState);
			repaint();
			
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			frog.tongue=true;
			repaint();
			
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState == MENU) {
				currentState = INSTRUCTION;
			}
			repaint();
			
		}
		
	}
		


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			frog.tongue=false;
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}