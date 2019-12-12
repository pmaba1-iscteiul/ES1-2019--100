package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private static final long serialVersionUID = -7740341565079488734L;
	private DataBase db;

	/**
	 * GraphApp constructor.
	 * Graphic interface settings 
	 * @param db - Data Base to test tools' and rules' quality and create corresponding histograms
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
	 * Create and return the data base of histogram for Tools' quality.
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
	 * Create and return the data base of histogram of Rules' quality.
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
	 * Evaluate iPlasma's and PMD's quality of is_Long_Method defect detection. 
	 * For this it is tested the quality of the given tool returning a list with defined 
	 * order of DCI, DII, ADCI and ADII counters.
	 * @param toolName - tool to be evaluated (PMD or iPlasma)
	 * @return list of results for the tool's quality 
	 */
	public List<Integer> compareTools(String toolName){

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
			else break;
			type = method.isIs_Long_Method();
			aux = auxCompare(type, result);
			int x = results.get(aux) + 1;
			results.set(aux, x);
		}
		return results;
	}	

	/**
	 * Evaluates the rules/thresholds created/defined by the user to detect defects relating 
	 * to is_Long_Method or is_Feature_Envy. For this it is tested the quality of the given 
	 * rule returning a list with defined order of DCI, DII, ADCI and ADII counters.
	 * @param ruleName - rule to be evaluated (name of existing rule in data base)
	 * @return list of results for the rule's quality 
	 */
	public List<Integer> compareRules(String ruleName){

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
						if (col.getRuleType().equals("is_Long_Method")) 
							type = rows.get(i).isIs_Long_Method();
						else if (col.getRuleType().equals("is_Feature_Envy")) 
							type = rows.get(i).isIs_Feature_Envy();
						else break;
						aux = auxCompare(type, result);
						results.set(aux, results.get(aux) + 1);
					}
				}
			}
		}
		return results;
	}

	/**
	 * This auxiliary method evaluates the result for each method, either DCI, DII, ADCI or ADII.
	 * Receives two booleans and calculates the index of the corresponding quality indicator in the 
	 * list of results of the given method so that the value on that same index can be incremented
	 * @param type - real result of is_Long_Method/is_Feature_Envy for the given method
	 * @param aval - rule calculated result of is_Long_Method/is_Feature_Envy for the given method
	 * @return integer corresponding to position of quality indicator on the results list
	 */
	public Integer auxCompare(boolean type, boolean aval){
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