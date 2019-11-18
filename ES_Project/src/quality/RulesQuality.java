package quality;

import java.util.ArrayList;
import excelReader.FileRow;
import utils.DataBase;

/**
 * @author Alexandra Belo
 * 
 * Class that evaluates the rules/thresholds created/defined by the user to detect defects 
 * relating to is_Long_Method or is_Feature_Envy.
 * For this evaluation counters are used for each quality indicator. 
 *
 */

public class RulesQuality {

	private int DCI = 0;
	private int DII = 0;	
	private int ADCI = 0;
	private int ADII = 0;
	private ArrayList<FileRow> rows;
	
	/**
	 * RulesQuality constructor  
	 * @param DataBase object 
	 */
	public RulesQuality (DataBase db/* objeto */) {
		this.rows.addAll(db.getExcel_file());
//		this.compare();
	}

	/**
	 * Returns the number of correctly identified defects by the rule
	 * @return the DCI counter
	 */
	
	public int getDCI() {
		return DCI;
	}

	/**
	 * Returns the number of incorrectly identified defects by the rule
	 * @return the DII counter
	 */
	
	public int getDII() {
		return DII;
	}

	/**
	 * Returns the number of correctly identified absence of defects by the rule
	 * @return the ADCI counter
	 */
	
	public int getADCI() {
		return ADCI;
	}

	/**
	 * Returns the number of incorrectly identified absence of defects by the rule
	 * @return the ADII counter
	 */
	
	public int getADII() {
		return ADII;
	}
		
// preciso de ver se é para comparar o resultado da regra com is_Long_Method ou com is_Feature_Envy
//	private void compare(){
//		for(FileRow method : rows) {
//			
//			
//			if(method.is_Long_Method == true) {
//				}
//			else {
//				
//			}	
//			
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
