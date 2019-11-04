package userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstScreen {
	
	private JFrame frame;
	private JFileChooser browser;
	private JButton next_button;
	private JTextField file_path;
	private JButton browser_button;
	
	public FirstScreen() {
		super();
		this.frame = new JFrame("Software Engineering App");
		this.browser = new JFileChooser();
		this.next_button = new JButton("Next");
		this.file_path = new JTextField(20);
		this.browser_button = new JButton("Browse");
		
		this.initialize();
		this.open();
	}
	
	private void open() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	
	private void initialize() {
		frame.setLayout(new FlowLayout());
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel path_panel = new JPanel(new BorderLayout());
		
		JPanel label_panel = new JPanel(new FlowLayout());
		JLabel file_label = new JLabel("Choose file");
		label_panel.add(file_label);
		path_panel.add(label_panel, BorderLayout.NORTH);
		
		JPanel south_panel = new JPanel(new BorderLayout());
		south_panel.add(file_path, BorderLayout.WEST);
		south_panel.add(browser_button, BorderLayout.EAST);
		path_panel.add(south_panel, BorderLayout.SOUTH);
		
		browser_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				browse();
			}
		});
		
		panel.add(next_button, BorderLayout.SOUTH);
		panel.add(path_panel, BorderLayout.NORTH);
		frame.add(panel);
		
	}
	
	private void browse() {
		int return_value = browser.showOpenDialog(null);
		if(return_value == JFileChooser.APPROVE_OPTION) {
			file_path.setText(browser.getSelectedFile().getAbsolutePath());
		}
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FirstScreen();
	}

}
