package userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow {
	
	private static int columns = 2;
	private static int rows = 1;
	
	private JFrame frame;
	private JButton rules_button;
	private JButton visualization_button;
	

	public MainWindow() {
		super();
		this.frame = new JFrame("Sfotware Engineering App");
		this.rules_button = new JButton("Add/Change Rules");
		this.visualization_button = new JButton("Vizualise Data");
		this.addContent();
		this.open();
	}
	
	private void open() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	private void addContent() {
		frame.setLayout(new GridLayout(rows, columns));
		
		JPanel top_buttons_panel = new JPanel();
		top_buttons_panel.add(rules_button);
		top_buttons_panel.add(visualization_button);
		
		frame.add(top_buttons_panel);
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainWindow();
	}

}
