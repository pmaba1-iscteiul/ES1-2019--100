package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset;

import quality.ToolsQuality;
import utils.DataBase; 


/**
 * @author Rodrigo Dinis
 * 
 * Class that allows view the system analysis results 
 * of the quality of iPlasma and PMD tools through a histogram.
 */
public class GraphApp extends ApplicationFrame {

	/**
	 * GraphApp constructor.
	 * Graphic interface settings 
	 * @param ToolsQuality toolsQuality 
	 */
	ToolsQuality toolsQuality;
	public GraphApp(ToolsQuality toolsQuality ) {
		super( "Histogram" ); 
		this.toolsQuality = toolsQuality;
		JFreeChart barChart = ChartFactory.createBarChart(
				"Histogram",           
				"",            
				"",            
				createDataset(),          
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
	 * Create and returns the data of histogram.
	 * @return CategoryDataset
	 */
	private CategoryDataset createDataset( ) {
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


//	public static void main(String[] args) {
//		DataBase db = new DataBase("/Users/r.dinis/Downloads/Long-Method.xlsx");
//		//System.out.println(db.getExcel_file());
//		ToolsQuality tq = new ToolsQuality(db);
//
//
//		GraphApp chart = new GraphApp(tq);
//
//		System.out.println("PDM:");
//		System.out.println("DCI =" + tq.getPMD_DCI());
//		System.out.println("DII =" + tq.getPMD_DII());
//		System.out.println("ADCI =" + tq.getPMD_ADCI());
//		System.out.println("ADII =" + tq.getPMD_ADII());
//		System.out.println("iPlasma:");
//		System.out.println("DCI =" + tq.getiPlasma_DCI());
//		System.out.println("DII =" + tq.getiPlasma_DII());
//		System.out.println("ADCI =" + tq.getiPlasma_ADCI());
//		System.out.println("ADII =" + tq.getiPlasma_ADII());
//
//
//	}
}