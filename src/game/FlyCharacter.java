package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class FlyCharacter extends FrogGameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	static Random random = new Random();
	void loadImage(String imageFile) {
		if(needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
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
	speed = 3;
	if(needImage) {
		loadImage("bluefly.png");
	}
}
	
void draw(Graphics g) {
	if(isActive) {
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
