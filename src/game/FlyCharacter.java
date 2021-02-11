package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class FlyCharacter extends FrogGameObject{
	public BufferedImage image;
	public static boolean needImage = true;
	public boolean gotImage = false;
	static Random random = new Random();
	String color= "green";
	static BufferedImage[] images= new BufferedImage[4];
	String[] colors= {"blue", "green", "purple", "red"};
	Random randomColor= new Random();
	
	void changeColor() {
		int num = randomColor.nextInt(4);
		image = images[num];
		color = colors[num];
		gotImage=true;
	
	}
	
	void kill() {
		isActive=false;
		System.out.println("killed");
	}
	void loadImage() {
		if(needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream("bluefly.png"));
				images[0] = image;
				image = ImageIO.read(this.getClass().getResourceAsStream("greenfly.png"));
				images[1] = image;
				image = ImageIO.read(this.getClass().getResourceAsStream("purplefly.png"));
				images[2] = image;
				image = ImageIO.read(this.getClass().getResourceAsStream("redfly.png"));
				images[3] = image;
				gotImage = true;
			}
			catch(Exception e) {
			System.out.println("no image");	
			}
			needImage = false;
		}
		
	}
FlyCharacter(){
	super(0, random.nextInt(300)+50, 70, 70);
	speed = 6;
	if(needImage) {
		loadImage();
	}
	changeColor();
}
	
void draw(Graphics g) {
	//g.setColor(Color.red);
	//g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	//.out.println("draw fly"+ x +y);
	if(isActive) {
		//System.out.println("fly not active");
	if(gotImage) {
		g.drawImage(image, x, y, width, height, null);
	}
	else {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
	}
}
void update(){
	x+= speed;
	super.update();
}

}
