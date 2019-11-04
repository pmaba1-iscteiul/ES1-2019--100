import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GraphApp extends JFrame {

	public GraphApp() {
		setSize(700,700);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paint(Graphics g) {
		g.drawLine(350, 50, 350, 650); //y axis
		g.drawLine(50,350,650,350);   //x axis
		mark_point(g,10,10);	 
	}
	
	private void mark_point(Graphics g,int x,int y) {
		g.fillOval(350+x, 350-y, 3, 3);	 //draw one point on graph
	}
	public static void main(String[] args) {
		new GraphApp();
	}
}
