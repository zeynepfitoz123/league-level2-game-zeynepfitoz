package game;

import java.awt.Rectangle;

public class FrogGameObject {
int x;
int y;
int width;
int height;
int speed = 0;
boolean isActive = true;
Rectangle collisionBox;
FrogGameObject(int x, int y, int width, int height) {
	this.x= x;
	this.y=y;
	this.width=width;
	this.height=height;
	this.collisionBox= new Rectangle(x, y, width, height);
}

void update(){
	collisionBox.setBounds(x, y, width, height);
}
}
