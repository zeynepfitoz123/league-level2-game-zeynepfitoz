package game;

import javax.swing.JFrame;

public class FrogGame {
	JFrame frame;
	FrogGamePanel object;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
public static void main(String[] args) {
	FrogGame object = new FrogGame();
	object.setup();
}

FrogGame(){
	frame=new JFrame();
	object=new FrogGamePanel();
}

void setup() {
	frame.add(object);
	frame.setSize(WIDTH, HEIGHT);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.addKeyListener(object);
}
}
