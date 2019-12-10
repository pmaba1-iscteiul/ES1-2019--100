package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset;

import quality.RulesQuality;
import quality.ToolsQuality;
import utils.DataBase; 


/**
 * @author Rodrigo Dinis
 * 
 * Class that allows view the system analysis results 
 * of the quality of iPlasma and PMD tools through a histogram.
 */
public class GraphApp extends ApplicationFrame {
	ToolsQuality toolsQuality;
	RulesQuality rulesQuality;

	/**
	 * GraphApp constructor.
	 * Graphic interface settings 
	 * @param rtQuality data to make the histograms, can be RulesQuality or ToolsQuality
	 */
	public GraphApp(Object rtQuality ) {
		super( "Histogram" );
		if(rtQuality instanceof ToolsQuality) {
			this.toolsQuality = (ToolsQuality) rtQuality;
			createHistogramTools();
		}
		else if(rtQuality instanceof RulesQuality) {
			this.rulesQuality = (RulesQuality) rtQuality;
			createHistogramRules();
		}
	}
	
	/**
	 * Creates the interface of Histogram for Rules 
	 */
	private void createHistogramRules() {
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
	private void createHistogramTools() {
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
		final String PMD =  "PMD";       
		final String iPlasma = "iPlasma";       

		final String dii = "DII";        
		final String adci = "ADCI";        
		final String adii = "ADII";        
		final String dci = "DCI";        

		final DefaultCategoryDataset dataset = 
				new DefaultCategoryDataset( );  

		dataset.addValue( toolsQuality.getPMD_DII() , PMD , dii );        
		dataset.addValue( toolsQuality.getPMD_ADCI() , PMD , adci );        
		dataset.addValue( toolsQuality.getPMD_ADII() , PMD , adii ); 
		dataset.addValue( toolsQuality.getPMD_DCI() , PMD , dci );           

		dataset.addValue( toolsQuality.getiPlasma_DII() , iPlasma , dii );        
		dataset.addValue( toolsQuality.getiPlasma_ADCI() , iPlasma , adci );       
		dataset.addValue( toolsQuality.getiPlasma_ADII() , iPlasma , adii );        
		dataset.addValue( toolsQuality.getiPlasma_DCI() , iPlasma , dci );

		return dataset; 
	}
	
	/**
	 * Create and returns the data base of histogram for Rules.
	 * @return CategoryDataset
	 */
	private CategoryDataset createDatasetRules( ) {
		final String Rules =  "Rules";       
		final String dii = "DII";        
		final String adci = "ADCI";        
		final String adii = "ADII";        
		final String dci = "DCI";        
		final DefaultCategoryDataset dataset = 
				new DefaultCategoryDataset( );  
		dataset.addValue( rulesQuality.getDII() , Rules , dii );        
		dataset.addValue( rulesQuality.getADCI() , Rules , adci );       
		dataset.addValue( rulesQuality.getADII() , Rules , adii );        
		dataset.addValue( rulesQuality.getDCI() , Rules , dci );

		return dataset; 
	}
	
}