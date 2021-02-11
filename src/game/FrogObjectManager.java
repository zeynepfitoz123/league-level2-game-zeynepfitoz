package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class FrogObjectManager implements ActionListener{
FrogCharacter frog;
ArrayList<FlyCharacter> flys= new ArrayList<FlyCharacter>();
Random random= new Random();
int score = 0;
boolean gameOver = false;
Font scoreFont;
FrogObjectManager(){
	scoreFont = new Font("Monospaced", Font.PLAIN, 68);
}

FrogObjectManager( FrogCharacter frog){
	this.frog=frog;
	
	
}

 void addFlyCharacter(){ 
	 flys.add(new FlyCharacter());
	 System.out.println("add fly"+flys.size());
 }
 
 
 void update() {
	 for (int i = 0; i < flys.size(); i++) {
			flys.get(i).update();
			if (flys.get(i).x >= FrogGame.WIDTH) {
				flys.get(i).kill();
			}
 }
	purgeObjects();
	 checkCollision();
 } 
 
 void draw(Graphics g) {
	 g.setFont(scoreFont);
	 g.setColor(Color.WHITE);
	 g.drawString("score "+ score, 100, 100);
	 frog.draw(g);
	 for(FlyCharacter fly : flys) {
		 fly.draw(g);
	 }
	//hi;
	 
 }
 
 void purgeObjects() {
		for (int i = 0; i < flys.size(); i++) {
			if (!flys.get(i).isActive) {
				flys.remove(i);
			}
		}
 }

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	addFlyCharacter();
}
 
 void checkCollision()  {
	 if(frog.tongue) {
		System.out.println("tongue");
		  Rectangle tongueBox = new Rectangle(frog.tongueX, frog.tongueY, frog.tongueW, frog.tongueH);
	for(FlyCharacter fly:flys) {
		if(fly.collisionBox.intersects(tongueBox)) {
			if(fly.color.equals(frog.color)){
				score+=1;
				fly.kill();
				frog.changeColor();
			}
			else {
				gameOver = true;
				return;
			}
		
		}
		
	}
	
	 }
	 
 }			
}
