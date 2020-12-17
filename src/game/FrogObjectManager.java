package game;

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

FrogObjectManager( FrogCharacter frog){
	this.frog=frog;
	
}

 void addFlyCharacter(){ 
	 flys.add(new FlyCharacter());
 }
 
 
 void update() {
	 for (int i = 0; i < flys.size(); i++) {
			flys.get(i).update();
			if (flys.get(i).y >= FrogGame.HEIGHT) {
				flys.get(i).isActive = false;
			}
 }
	
 } 
 
 void draw(Graphics g) {
	 frog.draw(g);
	 for(FlyCharacter fly : flys) {
		 fly.draw(g);
	 }
	
	 
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
	 Rectangle tongueBox = new Rectangle(frog.tongueX, frog.tongueY, frog.tongueW, frog.tongueH);
	for(FlyCharacter fly:flys) {
		if(fly.collisionBox.intersects(tongueBox)) {
			score+=1;
			fly.isActive= false;
		}
	}
 }			
}
