package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FrogGamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font startFont;
	Color swampGreen = new Color(46, 66, 29);
	Timer frameDraw;
	FrogCharacter frog = new FrogCharacter(355, 550, 90, 90);
	FrogObjectManager objectmanager = new FrogObjectManager(frog);
	Timer addFly= new Timer(2000, objectmanager);
	
	void updateMenuState(){
	
	}
	
	void updateInstructionState(){
		
	}
	
	void updateGameState(){
		objectmanager.update();
	}
	
	void updateEndState(){
		
	}
	
	void drawMenuState(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, FrogGame.WIDTH, FrogGame.HEIGHT);
		g.setFont(titleFont);
		g.setColor(swampGreen);
		g.drawString("Fly Catchers", 220, 130);
		g.setFont(startFont);
		g.setColor(swampGreen);
		g.drawString("Press ENTER To Start", 255,400);
		g.setFont(startFont);
		g.setColor(swampGreen);
		g.drawString("Press SPACE For Instructions", 215, 570);
	}
	
	
	void drawGameState(Graphics g){
	
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, FrogGame.WIDTH, FrogGame.HEIGHT);
		objectmanager.draw(g);
		g.drawString(FrogObjectManager.score, 100, 100);
	}
	
	void drawEndState(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0, 0, FrogGame.WIDTH, FrogGame.HEIGHT);
	}
	
	FrogGamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 68);
		startFont = new Font("Arial", Font.PLAIN, 30);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		addFly.start();
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
}

	@Override
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
		//System.out.println("action");
		repaint();
	}

	@Override
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
			else{
				currentState++;
			}
			System.out.println("current state "+ currentState);
			repaint();
			
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			frog.tongue=true;
		}
	}
		


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			frog.tongue=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}