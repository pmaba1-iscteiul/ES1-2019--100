package graph;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import excelReader.FileRow;
import rules.Column;
import rules.LineResult;
import utils.DataBase; 


/**
 * @author Rodrigo Dinis; Alexandra Belo
 * 
 * Class that allows view the system analysis results 
 * of the quality of iPlasma and PMD tools through a histogram.
 */
public class GraphApp extends ApplicationFrame {

	private DataBase db;

	/**
	 * GraphApp constructor.
	 * Graphic interface settings 
	 * @param rtQuality data to make the histograms, can be RulesQuality or ToolsQuality
	 */
	public GraphApp(DataBase db ) {
		super( "Histogram" );
		this.db = db;
	}

	/**
	 * Creates the interface of Histogram for Rules 
	 */
	public void createHistogramRules() {
		JFreeChart barChart = ChartFactory.createBarChart(
				"Histogram Rules",           
				"",            
				"",            
				createDatasetRules(),          
				PlotOrientation.VERTICAL,           
				true, true, false);

		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		setContentPane( chartPanel ); 
		this.pack( );        
		this.setVisible( true ); 
		this.setResizable(false);
	}
	/**
	 * Creates the interface of Histogram for Tools 
	 */
	public void createHistogramTools() {
		JFreeChart barChart = ChartFactory.createBarChart(
				"Histogram Tools",           
				"",            
				"",            
				createDatasetTools(),          
				PlotOrientation.VERTICAL,           
				true, true, false);

		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		setContentPane( chartPanel ); 
		this.pack( );        
		this.setVisible( true ); 
		this.setResizable(false);
	}

	/**
	 * Create and returns the data base of histogram for Tools.
	 * @return CategoryDataset
	 */
	private CategoryDataset createDatasetTools( ) {
		final List<String> tools = List.of("PMD", "iPlasma");
		final List<String> indicators = List.of("DCI", "DII", "ADCI", "ADII");
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

		List<Integer> results; 
		for (int i = 0; i < tools.size(); i++) {
			results = compareTools(tools.get(i));
			for (int j = 0; j < indicators.size(); j++) {
				dataset.addValue(results.get(j) , tools.get(i) , indicators.get(j));
			}
		}      
		return dataset; 
	}

	/**
	 * Create and returns the data base of histogram for Rules.
	 * @return CategoryDataset
	 */
	private CategoryDataset createDatasetRules( ) {
		final List<String> rules = new ArrayList<String>();

		List<Column> columns = db.getColumns();
		for(Column col : columns)
			rules.add(col.getRuleName());

		final List<String> indicators = List.of("DCI", "DII", "ADCI", "ADII");       
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

		List<Integer> results; 
		for (int i = 0; i < rules.size(); i++) {
			results = compareRules(rules.get(i));
			for (int j = 0; j < indicators.size(); j++) {
				dataset.addValue(results.get(j) , rules.get(i) , indicators.get(j));
			}
		}      
		return dataset; 
	}


	/**
	 * Evaluates iPlasma's and PMD's quality of defect detection of is_Long_Method.
	 * For this evaluation counters are used for each quality indicator. This tests the quality 
	 * of the given tool returning a list with DCI, DII, ADCI and ADII counter
	 * @return list of results for the tools' quality 
	 */
	private List<Integer> compareTools(String toolName){

		List<FileRow> rows = db.getExcel_file();
		
		int aux;
		boolean type;
		boolean result = false;
		List<Integer> results = Arrays.asList(0,0,0,0);
		
		for(FileRow method : rows) {
			if(toolName.equals("PMD"))
				result = method.isPMD();
			else if (toolName.equals("iPlasma"))
				result = method.isiPlasma();
			type = method.isIs_Long_Method();
			aux = aux_compare(type, result);
			int x = results.get(aux) + 1;
			results.set(aux, x);
		}
		return results;
	}	

	/**
	 * Evaluates the rules/thresholds created/defined by the user to detect defects 
	 * relating to is_Long_Method or is_Feature_Envy.
	 * For this evaluation counters are used for each quality indicator. 
	 * @return list of results for the rules' quality 
	 */

	private List<Integer> compareRules(String ruleName){

		List<FileRow> rows = db.getExcel_file();
		List<Column> columns = db.getColumns();

		int aux;
		boolean type;
		boolean result;
		List<Integer> results = Arrays.asList(0,0,0,0);

		for(Column col : columns) {
			if (col.getRuleName().equals(ruleName)) {
				List<LineResult> lineResults = col.getArray();
				for (int i = 0; i < rows.size(); i++) {
					if(rows.get(i).getMethodID() == lineResults.get(i).getMethodID()) {
						result = lineResults.get(i).isResult();
						if (col.getRuleType().equals("is_Long_Method")) {
							type = rows.get(i).isIs_Long_Method();
							aux = aux_compare(type, result);
							results.set(aux, results.get(aux) + 1);
						} else if (col.getRuleType().equals("is_Feature_Envy")) {
							type = rows.get(i).isIs_Feature_Envy();
							aux = aux_compare(type, result);
							results.set(aux, results.get(aux) + 1);
						}
					}
				}
			}
		}
		return results;
	}

	private Integer aux_compare(boolean type, boolean aval){
		if(type == true) {
			if(aval == true) 
				return 0;
			else 
				return 3;
		} else {
			if(aval == false) 
				return 2;
			else 
				return 1;	
		}
	}
}