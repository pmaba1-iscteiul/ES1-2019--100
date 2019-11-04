package userinterface;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstScreen {
	
	private JFrame frame;
	private JFileChooser browser;
	private JButton next_button;
	private JTextField file_path;
	private JButton browser_button;
	private JPanel panel;
	
	public FirstScreen() {
		super();
		this.frame = new JFrame("Software Engineering App");
		this.browser = new JFileChooser();
	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
