package userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainWindow {
		
	private JFrame frame;
	private JPanel main_panel;
	private JPanel visualization_panel;
	private JPanel rules_alteration_panel;
	private JPanel rule_creation_defect_panel;

	public MainWindow() {
		super();
		this.frame = new JFrame("Software Engineering App");
		this.main_panel = new JPanel();
		this.visualization_panel = new JPanel();
		this.rules_alteration_panel = new JPanel();
		this.rule_creation_defect_panel = new JPanel(new BorderLayout());
		this.addContentMain();
		this.addContentVisualization();
		this.addContentRulesAlteration();
		this.addContentRuleDefect();
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
		main_panel = new JPanel(new GridLayout(2, 2));
		JButton rules_button = new JButton("Add/Change Rules");
		JButton visualization_button = new JButton("Vizualise Data");
		JButton tools_quality_button = new JButton("Tools Quality");
		main_panel.add(rules_button);
		main_panel.add(visualization_button);
		main_panel.add(tools_quality_button);
		
		rules_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(main_panel, rules_alteration_panel);
			}
		});
		
		visualization_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(main_panel, visualization_panel);
			}
		});
		
		tools_quality_button.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tools quality");
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
				System.out.println("Graphics Button");
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

	private void addContentRulesAlteration() {
		rules_alteration_panel.setLayout(new GridLayout(2,2));
		JButton add_rules_button = new JButton("Add Rules");
		JButton change_rulles_button = new JButton("Change Rules");
		JButton back_button = new JButton("Back");
		rules_alteration_panel.add(add_rules_button);
		rules_alteration_panel.add(change_rulles_button);
		rules_alteration_panel.add(back_button);
		
		add_rules_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(rules_alteration_panel, rule_creation_defect_panel);
			}
		});
		
		change_rulles_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Change Rules");
			}
		});
		
		back_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(rules_alteration_panel, main_panel);
			}
		});
	}
	
	private void addContentRuleDefect() {
		JPanel defect_buttons_panel = new JPanel(new BorderLayout());
		JButton is_long_button = new JButton("is Long");
		JButton is_feature = new JButton("is feature envy");
		
		defect_buttons_panel.add(is_long_button, BorderLayout.WEST);
		defect_buttons_panel.add(is_feature, BorderLayout.EAST);
		
		rule_creation_defect_panel.add(defect_buttons_panel, BorderLayout.CENTER);
		rule_creation_defect_panel.add(new JLabel("The new rule describes what defect?"), BorderLayout.NORTH);
		
		is_long_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rule_creation(Defects.is_long);
			}
		});
		
		is_feature.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rule_creation(Defects.is_feature_envy);
			}
		});
		
		JPanel progress_buttons_panel = new JPanel(new BorderLayout());
		JButton back_button = new JButton("Back");
		
		progress_buttons_panel.add(back_button, BorderLayout.WEST);
		rule_creation_defect_panel.add(progress_buttons_panel, BorderLayout.SOUTH);
		
		back_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(rule_creation_defect_panel, main_panel);
			}
		});
		
		
		
	}

	private void rule_creation(Defects defect) {
		JPanel metrics_panel = new JPanel(new BorderLayout());
		JPanel left_panel = new JPanel(new BorderLayout());
		JPanel right_panel = new JPanel(new BorderLayout());
		
		DefaultListModel<String> list = new DefaultListModel<String>();
		JList metric_list = new JList(list);
		JScrollPane scroll_list = new JScrollPane(metric_list);
		left_panel.add(scroll_list, BorderLayout.NORTH);
		
		List<String> selected_metrics = new ArrayList<String>();
		
		metric_list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting())
					if(metric_list.getSelectedValue() != null) {
						selected_metrics.add(metric_list.getSelectedValue().toString());
						list.removeElement(metric_list.getSelectedValue());
						
					}
			}
		});
		
		JLabel metrics_choosen_label = new JLabel("Metrics alredy choosen:\n");
		right_panel.add(metrics_choosen_label, BorderLayout.NORTH);
		
		JButton next_button = new JButton("Next");
		JButton back_button = new JButton("Back");
		
		next_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		back_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(metrics_panel, main_panel);
			}
		});
		
		left_panel.add(back_button, BorderLayout.SOUTH);
		right_panel.add(next_button,BorderLayout.SOUTH);
		
		metrics_panel.add(left_panel,BorderLayout.WEST);
		metrics_panel.add(right_panel, BorderLayout.EAST);
		
		/*
		 * test begin
		 */
			list.addElement("metrica 1");
			list.addElement("Metrica 2");
		/*
		 * test end
		 */
		
		buildScreen(rule_creation_defect_panel, metrics_panel);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainWindow();
	}

}
