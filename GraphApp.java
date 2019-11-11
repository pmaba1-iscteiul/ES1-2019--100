import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class GraphApp extends ApplicationFrame {
   //Classe criada para comparar a qualidade das ferramentas atraves de um histograma
	
   public GraphApp( String applicationTitle , String chartTitle ) {
      super( applicationTitle );       
   //cria a interface do grafico 
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
   
   private CategoryDataset createDataset( ) {
	   //cria a base de dados do grafico (base de dados teste)
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
   
   public static void main( String[ ] args ) {
	   GraphApp chart = new GraphApp("applicationTitle", 
         "chartTitle");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}