package userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import rules.Column;
import rules.Rule;
import rules.RulePart;
import utils.DataBase;
import utils.Defect;
import utils.LogicOperator;
/**
 * Main visualization window, responsible for all the mechanics in the system and all user interface.
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
	private JPanel ruleAlteration;
	private JPanel graphicChooser;
	private List<String> condition = new ArrayList<>();
	private Stack<JPanel> metrics = new Stack<>();
	private DataBase data;
	// create condition panel
	private String condition_comparator = "";
	private boolean isSelectible = true;

	/**
	 * Initializes all class atr
	 * @param data
	 */
	public MainWindow(DataBase data) {
		super();
		this.data = data;
	}

	public void init() {
		this.frame = new JFrame("Software Engineering App");
		this.main_panel = new JPanel();
		this.visualization_panel = new JPanel();
		this.rules_alteration_panel = new JPanel();
		this.rule_creation_defect_panel = new JPanel(new BorderLayout());
		this.ruleAlteration = new JPanel();
		this.graphicChooser = new JPanel(new BorderLayout());
		this.addContentMain();
		this.addContentVisualization();
		this.addContentRulesAlteration();
		this.addContentRuleDefect();
		this.addContentChooseGraphic();
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
	 * Creates main windown. Responsible for the menu creation.
	 */
	private void addContentMain() {
		main_panel = new JPanel(new GridLayout(2, 1));
		JButton rules_button = new JButton("Add/Change Rules");
		JButton visualization_button = new JButton("Visualize Data");
		main_panel.add(rules_button);
		main_panel.add(visualization_button);

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
				buildScreen(visualization_panel, graphicChooser);
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

	private void addContentChooseGraphic() {
		JPanel buttonsPanel = new JPanel(new GridLayout(2, 2));

		JButton rules = new JButton("Rules");
		rules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GraphApp g = new GraphApp(data);
				g.createHistogramRules();
				if(g.open())
					buildScreen(graphicChooser, main_panel);
			}
		});
		buttonsPanel.add(rules);

		JButton tools = new JButton("Tools");
		tools.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GraphApp g = new GraphApp(data);
				g.createHistogramTools();
				if(g.open())
					buildScreen(graphicChooser, main_panel);
			}
		});
		buttonsPanel.add(tools);

		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(graphicChooser, main_panel);
			}
		});
		buttonsPanel.add(back);

		graphicChooser.add(new JLabel("What Graphic?"), BorderLayout.NORTH);
		graphicChooser.add(buttonsPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates the windows responsible by allowing the user visualize the excel file
	 */
	private void createTableView() {
		JPanel table_panel = new JPanel();

		JButton back_button = new JButton("Back");
		back_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(table_panel, main_panel);
			}
		});
		table_panel.setLayout(new BorderLayout());

		List<String> results = data.getResults();
		List<Column> col = data.getColumns();
		String[][] dataTable = new String[results.size()][12+col.size()];
		for(int i = 0; i < dataTable.length; i++) {
			dataTable[i] = results.get(i).split(" ");
		}
		String[] dataNames = new String[12+col.size()];
		String[] collName = {"MethodID", "package", "class", "method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "iPlasma",
				"PMD", "is_feature_envy"};

		for(int i = 0; i < dataNames.length; i++) {
			if(i < 12) {
				dataNames[i] = collName[i];
			}else {
				dataNames[i] = col.get(i-12).getRuleName();
			}
		}

		JTable table = new JTable(dataTable, dataNames);
		JScrollPane table_scroll = new JScrollPane(table);
		table_panel.add(table_scroll, BorderLayout.NORTH);

		table_panel.add(back_button, BorderLayout.SOUTH);
		buildScreen(visualization_panel, table_panel);
	}

	/**
	 * Creates the menu that allows the user to choose if he what's to add a new rule or change a existing one.
	 * 
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
				//				buildScreen(rules_alteration_panel, ruleAlteration);
				addContentRuleChanging();;
				buildScreen(rules_alteration_panel,ruleAlteration);
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
	 * Create a panel that allows changing the rule already Created
	 */
	private void addContentRuleChanging() {
		ruleAlteration = new JPanel(new BorderLayout());
		ruleAlteration.add(new JLabel("What's the rule you want to change?"), BorderLayout.NORTH);
		DefaultListModel<Rule> list = new DefaultListModel<>();
		JList<Rule> rule_list = new JList<Rule>(list);
		JScrollPane scroll_list = new JScrollPane(rule_list);
		list.clear();
		list.addAll(data.getRules());
		ruleAlteration.add(scroll_list,BorderLayout.EAST);

		rule_list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting())
					if(rule_list.getSelectedValue() != null) {
						Rule r = (Rule) rule_list.getSelectedValue();
						createRule(r.getRuleType(), r.getRuleName(), false);
					}
			}
		});

		JButton backButton = new JButton("Back");
		ruleAlteration.add(backButton, BorderLayout.SOUTH);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(ruleAlteration, main_panel);
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
				if(!ruleNameTextField.getText().isBlank() && !data.getRulesName().contains(ruleNameTextField.getText())) {
					createRule(Defect.is_long, ruleNameTextField.getText(), true);
					ruleNameTextField.setText("");
				}
			}
		});

		is_feature.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!ruleNameTextField.getText().isBlank() && !data.getRulesName().contains(ruleNameTextField.getText())) {
					createRule(Defect.is_feature_envy, ruleNameTextField.getText(), true);
					ruleNameTextField.setText("");
				}
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
	private void createRule(Defect defect, String ruleName, boolean isScratch) {
		JPanel metrics_panel = new JPanel(new BorderLayout());
		JPanel left_panel = new JPanel(new BorderLayout());
		JPanel right_panel = new JPanel(new BorderLayout());

		DefaultListModel<String> list = new DefaultListModel<String>();
		JList<String> metric_list = new JList<String>(list);
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
		if(isScratch)
			buildScreen(rule_creation_defect_panel, metrics_panel);
		else
			buildScreen(ruleAlteration, metrics_panel);
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
				condition_comparator = "";
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
									condition_comparator.equals(">") || condition_comparator.equals(">=")) {
								parts.add(new RulePart(metric, lim, condition_comparator));
								condition_comparator = "";
								if (metrics.size() == 1 && parts.size() == 1) {
									data.addRule(new Rule(parts, (List<LogicOperator>)new ArrayList<LogicOperator>(), ruleName, defect));
									buildScreen(metrics.pop(), main_panel);
								}else if(metrics.size() == 1) {
									buildScreen(metrics.pop(), joinCondition(parts, ruleName, defect));
								}else {
									buildScreen(metrics.pop(), metrics.peek());
								}
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
		List<RulePart> partsFinalOrder = new ArrayList<RulePart>();
		List<LogicOperator> logicOperators = new ArrayList<LogicOperator>();
		JPanel panel = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new GridLayout(2,1));
		JPanel bottom = new JPanel();

		JLabel topLabel = new JLabel("Condition: ");

		panel.add(topLabel, BorderLayout.NORTH);

		DefaultListModel<RulePart> list = new DefaultListModel<RulePart>();
		JList<RulePart> metricList = new JList<RulePart>(list);
		JScrollPane scroll_list = new JScrollPane(metricList);
		list.addAll(parts);
		panel.add(scroll_list, BorderLayout.WEST);

		metricList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting() && metricList.getSelectedValue() != null) 
					if(isSelectible) {
						RulePart r = metricList.getSelectedValue();
						list.removeElement(r);
						partsFinalOrder.add(r);
						topLabel.setText(topLabel.getText() + " " + r.toString());
						isSelectible = false;
						if(list.isEmpty()) {
							if(partsFinalOrder.size() == parts.size() && logicOperators.size() == parts.size() - 1 ) {
								data.addRule(new Rule(partsFinalOrder, logicOperators, ruleName, defect));
								buildScreen(panel, main_panel);
								isSelectible = true;
							}
						}
					}
			}
		});


		JButton and_button = new JButton("AND");
		JButton or_button = new JButton("OR");
		right.add(and_button);
		right.add(or_button);
		panel.add(right, BorderLayout.EAST);

		and_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!isSelectible) {
					logicOperators.add(LogicOperator.AND);
					topLabel.setText(topLabel.getText() + " AND" );
					isSelectible = true;
					frame.pack();
				}

			}
		});

		or_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!isSelectible) {
					logicOperators.add(LogicOperator.OR);
					topLabel.setText(topLabel.getText() + " OR" );
					isSelectible = true;
					frame.pack();
				}
			}
		});

		JButton cancel_button = new JButton("Cancel");
		bottom.add(cancel_button, BorderLayout.WEST);
		panel.add(bottom, BorderLayout.SOUTH);

		cancel_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildScreen(panel, main_panel);
				isSelectible = true;
			}
		});

		return panel;
	}

//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//			new MainWindow(new DataBase("D:/Computer_Files/Downloads/Long-Method.xlsx")).init();
//		}

}
