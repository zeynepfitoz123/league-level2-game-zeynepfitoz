package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class FrogCharacter extends FrogGameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	boolean tongue = false;
	int tongueX;
	int tongueY;
	int tongueW= 10;
	int tongueH= 540;
	String color= "green";
	static BufferedImage[] images=new BufferedImage[4];
	static String[] colors= {"blue", "green", "purple", "red"};
	Random random= new Random();
	
	void changeColor() {
		int num = random.nextInt(4);
		System.out.println("frog color "+num);
		image = images[num];
		color = colors[num];
	
	}
	
	void loadImage() {
		if(needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream("bluefrog.png"));
				images[0] = image;
				image = ImageIO.read(this.getClass().getResourceAsStream("greenfrog.png"));
				images[1] = image;
				image = ImageIO.read(this.getClass().getResourceAsStream("purplefrog.png"));
				images[2] = image;
				image = ImageIO.read(this.getClass().getResourceAsStream("redfrog.png"));
				images[3] = image;
				gotImage = true;
			}
			catch(Exception e) {
			System.out.println("no image");	
			}
			needImage = false;
		}
		
	}

	FrogCharacter(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 15;
		if(needImage) {
			loadImage();
		}
		changeColor();
	}
	
      void draw(Graphics g) {
    	  //System.out.println("draw frog");
		if(gotImage) {
			g.drawImage(image, x, y, width, height, null);
		}
		else {
			g.setColor(Color.black);
			g.fillRect(x, y, width, height);
		}
		
		//g.setColor(Color.RED);
		//g.drawRect(collisionBox.x, collisionBox.y,collisionBox.width , collisionBox.height);
		
		//System.out.println("draw frog");
		if(tongue) {
			tongueX= x+40;
			tongueY = y-500;
			g.setColor(Color.PINK);
			g.fillRect(tongueX, tongueY, tongueW, tongueH);
		}
	}
      
      public void up() {
    	  y-=speed;
      }
      
      public void down() {
    	  y+=speed;
      }
      
      public void right() {
    	  x+=speed;
      }
      
      public void left() {
    	  x-=speed;
      }
     void update(){
    	  super.update();
      }

	


}
