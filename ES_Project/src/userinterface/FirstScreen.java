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

import utils.DataBase;
/**
 * This class creates de the first windows of th system. In it the user can choose what file to load
 * 
 * @author João André
 *
 */
public class FirstScreen {
	
	private JFrame frame;
	private JFileChooser browser;
	private JButton next_button;
	private JTextField file_path;
	private JButton browser_button;
	
	/**
	 * Initializaes all class atribtutes and start create the window
	 */
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
	/**
	 * Set several parameters related to the JFrame. Sets visibility to true, default close operation to exit on close and
	 * packs the frame.
	 */
	private void open() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	/**
	 * Creates the layout of the window.
	 */
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
		
		next_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!file_path.getText().isEmpty()) {
					new MainWindow(new DataBase(file_path.getText()));
					frame.dispose();
				}
			}
		});
		
		frame.add(panel);
		
	}
	/**
	 * Opens a dialog box. In the box the user can navigate through the file system and choose the file to open.
	 */
	private void browse() {
		int return_value = browser.showOpenDialog(null);
		if(return_value == JFileChooser.APPROVE_OPTION) {
			file_path.setText(browser.getSelectedFile().getAbsolutePath());
		}
	}
	/**
	 * Returns the choosen file path.
	 * @return 
	 */
	public String getFile_path() {
		return file_path.getText();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FirstScreen();
	}

}
