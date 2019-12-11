package userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;

import rules.LogicOperator;
import rules.Rule;
import rules.RulePart;
import utils.DataBase;
/**
 * Main visualization window, responsible for all the mechanics in the system and all user interface.
 * 
 * Not fully implemented yet. 
 * 
 * @author João André
 *
 */
public class MainWindow {

	private JFrame frame;
	private JPanel main_panel;
	private JPanel visualization_panel;
	private JPanel rules_alteration_panel;
	private JPanel rule_creation_defect_panel;
	private List<String> condition = new ArrayList<>();
	private Stack<JPanel> metrics = new Stack<>();
	private DataBase data;
	// create condition panel
	private String condition_comparator = "";
	//	int condition_limit = 0;

	/**
	 * Initializes all class atr
	 * @param data
	 */
	public MainWindow(DataBase data) {
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

		this.data = data;
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
	 * Creates main windown. Responsible for the menu creation.
	 */
	private void addContentMain() {
		main_panel = new JPanel(new GridLayout(2, 2));
		JButton rules_button = new JButton("Add/Change Rules");
		JButton visualization_button = new JButton("Visualize Data");
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
	/**
	 * Creates the windows responsible by allowing the user visualize the excel file
	 */
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
	/**
	 * Creates the menu that allows the user to choose if he what's to add a new rule or change a existing one.
	 * 
	 * Still missing interface for changing rules
	 */
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

	/**
	 * Panel that allows the user to choose the defect that's going to be represented.
	 * 
	 */
	private void addContentRuleDefect() {
		JPanel defect_buttons_panel = new JPanel(new BorderLayout());
		JButton is_long_button = new JButton("is Long");
		JButton is_feature = new JButton("is feature envy");

		defect_buttons_panel.add(is_long_button, BorderLayout.WEST);
		defect_buttons_panel.add(is_feature, BorderLayout.EAST);

		JPanel ruleNamePanel = new JPanel(new GridLayout(3, 1));

		JTextField ruleNameTextField = new JTextField();

		ruleNamePanel.add(new JLabel("The new rule describes what defect?"));
		ruleNamePanel.add(new JLabel("Rule Name:"));
		ruleNamePanel.add(ruleNameTextField);

		rule_creation_defect_panel.add(defect_buttons_panel, BorderLayout.CENTER);
		rule_creation_defect_panel.add(ruleNamePanel, BorderLayout.NORTH);

		is_long_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!ruleNameTextField.getText().isBlank() && !data.getRulesName().contains(ruleNameTextField.getText()))
					createRule(Defect.is_long, ruleNameTextField.getText());
			}
		});

		is_feature.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!ruleNameTextField.getText().isBlank() && !data.getRulesName().contains(ruleNameTextField.getText()))
					createRule(Defect.is_feature_envy, ruleNameTextField.getText());
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

	/**
	 * Shows a list of metrics. The user can select these metrics to construct the rule
	 * @param defect
	 */
	private void createRule(Defect defect, String ruleName) {
		JPanel metrics_panel = new JPanel(new BorderLayout());
		JPanel left_panel = new JPanel(new BorderLayout());
		JPanel right_panel = new JPanel(new BorderLayout());

		DefaultListModel<String> list = new DefaultListModel<String>();
		JList metric_list = new JList(list);
		JScrollPane scroll_list = new JScrollPane(metric_list);
		left_panel.add(scroll_list, BorderLayout.NORTH);

		JLabel metrics_title_label = new JLabel("Metrics alredy choosen:");
		right_panel.add(metrics_title_label, BorderLayout.NORTH);

		JLabel metrics_choosen_label = new JLabel();
		right_panel.add(metrics_choosen_label, BorderLayout.CENTER);

		List<RulePart> parts = new ArrayList<RulePart>();

		metric_list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting())
					if(metric_list.getSelectedValue() != null) {
						metrics.add(createConditionPanel(metric_list.getSelectedValue().toString(), defect, parts, ruleName));
						metrics_choosen_label.setText(metrics_choosen_label.getText() + metric_list.getSelectedValue().toString() + ";");
						list.removeElement(metric_list.getSelectedValue());
						frame.pack();
					}
			}
		});

		JButton next_button = new JButton("Next");
		JButton back_button = new JButton("Back");

		next_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(metrics_panel, metrics.peek());
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
		list.addElement("LOC");
		list.addElement("CYCLO");
		list.addElement("ATFD");
		list.addElement("LAA");
		/*
		 * test end
		 */

		buildScreen(rule_creation_defect_panel, metrics_panel);

	}

	/**
	 * Creates a new JPanel for the metrics selected by the user. The JPanel enables the user to say the limit and comparator
	 * to use e the rules. In essense creates the condition.
	 * @param metric_name
	 * @return
	 */
	private JPanel createConditionPanel(String metric, Defect defect, List<RulePart> parts, String ruleName) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel centerLabel = new JLabel("Limit: ");

		JPanel top = new JPanel(new GridLayout(1, 3));
		top.add(new JLabel("Metric: " + metric + "   "));
		top.add(centerLabel);
//		JTextField limit_text = new JTextField();
		NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
		JFormattedTextField limit_text = new JFormattedTextField(formatter);
		top.add(limit_text);
		
		JPanel center = new JPanel(new GridLayout(1,4));
		JButton grater_button = new JButton(">");
		JButton grater_equals_button = new JButton(">=");
		JButton lesser_button = new JButton("<");
		JButton lesser_equals_button = new JButton("<=");

		center.add(grater_equals_button);
		center.add(grater_button);
		center.add(lesser_button);
		center.add(lesser_equals_button);

		grater_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				condition_comparator = ">";
				centerLabel.setText("Comparator: > " + "Limit: ");
				frame.pack();
			}
		});

		grater_equals_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				condition_comparator = ">=";
				centerLabel.setText("Comparator: >= " + "Limit: ");
				frame.pack();
			}
		});

		lesser_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				condition_comparator = "<";
				centerLabel.setText("Comparator: < " + "Limit: ");
				frame.pack();
			}
		});

		lesser_equals_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				condition_comparator = "<=";
				centerLabel.setText("Comparator: <= " + "Limit: ");
				frame.pack();
			}
		});

		JPanel bottom = new JPanel(new BorderLayout());
		JButton back_button = new JButton("Back");
		JButton next_Button = new JButton("Next");
		bottom.add(back_button, BorderLayout.WEST);
		bottom.add(next_Button, BorderLayout.EAST);

		back_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				condition = new ArrayList<>();
				buildScreen(panel, main_panel);
			}
		});

		next_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!limit_text.getText().isBlank()) {
					try {
						int lim = Integer.parseInt(limit_text.getText());
						if(lim > 0)
							if(condition_comparator.equals("<") || condition_comparator.equals("<=") ||
									condition_comparator.equals(">") || condition_comparator.equals(">="))
								if (metrics.size() == 1 && parts.size() != 0 ) {
									parts.add(new RulePart(metric, lim, condition_comparator));
									condition_comparator = "";
									buildScreen(metrics.pop(), main_panel);
								}else if(metrics.size() == 1) {
									parts.add(new RulePart(metric, lim, condition_comparator));
									new Rule(parts, (List<LogicOperator>)new ArrayList<LogicOperator>(), ruleName, defect);
									condition_comparator = "";
									buildScreen(metrics.pop(), joinCondition(parts, ruleName, defect));
								}else {
									buildScreen(metrics.pop(), metrics.peek());
									condition_comparator = "";
								}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					condition.add("O Objeto Com a condição");
				}
			}
		});

		panel.add(top, BorderLayout.NORTH);
		panel.add(center, BorderLayout.CENTER);
		panel.add(bottom, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Enables the user to join several conditions previously created. Those are join with a logic operator.
	 * @return
	 */
	private JPanel joinCondition(List<RulePart> parts, String ruleName, Defect defect) {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new GridLayout(2,1));
		JPanel bottom = new JPanel();

		DefaultListModel<String> list = new DefaultListModel<String>();
		JList metric_list = new JList(list);
		JScrollPane scroll_list = new JScrollPane(metric_list);

		panel.add(scroll_list, BorderLayout.WEST);

		JButton and_button = new JButton("AND");
		JButton or_button = new JButton("OR");
		right.add(and_button);
		right.add(or_button);
		panel.add(right, BorderLayout.EAST);

		and_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		or_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton cancel_button = new JButton("Cancel");
		JButton nextButton = new JButton("Next");
		bottom.add(cancel_button, BorderLayout.WEST);
		bottom.add(nextButton, BorderLayout.EAST);
		panel.add(bottom, BorderLayout.SOUTH);

		cancel_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(panel, main_panel);
			}
		});

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		return panel;
	}

	//	public static void main(String[] args) {
	//		// TODO Auto-generated method stub
	//		new MainWindow();
	//	}

}
