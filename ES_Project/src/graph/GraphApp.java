package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 


/**
 * @author Rodrigo Dinis
 * 
 * Class that allows view the system analysis results 
 * of the quality of iPlasma and PMD tools through a histogram.
 */
public class GraphApp extends ApplicationFrame {

	/**
	 * GraphApp constructor 
	 */
	public GraphApp( String applicationTitle , String chartTitle ) {
		super( "" );       
		JFreeChart barChart = ChartFactory.createBarChart(
				chartTitle,           
				"x_axis",            
				"y_axis",            
				createDataset(),          
				PlotOrientation.VERTICAL,           
				true, true, false);

		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		setContentPane( chartPanel ); 
	}

	/**
	 * Create and returns the data of histogram.
	 * @return CategoryDataset
	 */
	private CategoryDataset createDataset( ) {
		final String iPlasma = "iPlasma";        
		final String PMD = "PMD";        

		final String a = "x_a";        
		final String b = "x_b";        
		final String c = "x_c";        
		final String d = "x_d";        

		final DefaultCategoryDataset dataset = 
				new DefaultCategoryDataset( );  

		dataset.addValue( 1.0 , iPlasma , a );        
		dataset.addValue( 3.0 , iPlasma , c );        
		dataset.addValue( 5.0 , iPlasma , b ); 
		dataset.addValue( 5.0 , iPlasma , d );           

		dataset.addValue( 5.0 , PMD , a );        
		dataset.addValue( 6.0 , PMD , c );       
		dataset.addValue( 100.0 , PMD , b );        
		dataset.addValue( 4.0 , PMD , d );


		return dataset; 
	}
	
//  Para Testar
//	public static void main( String[ ] args ) {
//		GraphApp chart = new GraphApp("applicationTitle", 
//				"chartTitle");
//		chart.pack( );        
//		chart.setVisible( true ); 
//	}
}