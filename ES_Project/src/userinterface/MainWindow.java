package userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainWindow {
	
	private static int columns = 2;
	private static int rows = 1;
	
	private JFrame frame;
	private JPanel main_panel;
	private JPanel visualization_panel;
	private JPanel table_panel;

	public MainWindow() {
		super();
		this.frame = new JFrame("Sfotware Engineering App");
		this.main_panel = new JPanel();
		this.visualization_panel = new JPanel();
		this.addContentMain();
		this.addContentVisualization();
		this.frame.add(main_panel);
		this.open();
	}
	/**
	 * Changes between two JPanels
	 * @param current
	 * @param next
	 */
	private void buildScreen(JPanel current, JPanel next) {
		frame.remove(current);
		frame.add(next);
		frame.pack();
	}
	/**
	 * Frame is visible, the default close operation is exit and packs the frame
	 */
	private void open() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	/**
	 * Creates main windown
	 */
	private void addContentMain() {
		main_panel = new JPanel(new GridLayout(rows, columns));
		JButton rules_button = new JButton("Add/Change Rules");
		JButton visualization_button = new JButton("Vizualise Data");
		main_panel.add(rules_button);
		main_panel.add(visualization_button);
		
		visualization_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(main_panel, visualization_panel);
			}
		});
		
	}
	/**
	 * Creates window with visualization options 
	 */
	private void addContentVisualization() {
		visualization_panel.setLayout(new GridLayout(2,2));
		JButton table = new JButton("Table");
		JButton graphics = new JButton("Graphics");
		JButton back = new JButton("Back");
		
		visualization_panel.add(table);
		visualization_panel.add(graphics);
		visualization_panel.add(back);
		
		table.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createTableView();
			}
		});
		
		graphics.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Graohics Button");
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(visualization_panel, main_panel);
			}
		});	
	}

	private void createTableView() {
		JPanel table_panel = new JPanel();
		table_panel.setLayout(new BorderLayout());
		JTable table = new JTable();
		JScrollPane table_scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table_panel.add(table_scroll, BorderLayout.NORTH);
		
		JButton back_button = new JButton("Back");
		back_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(table_panel, main_panel);
			}
		});
		table_panel.add(back_button, BorderLayout.SOUTH);
		
		buildScreen(visualization_panel, table_panel);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainWindow();
	}

}
