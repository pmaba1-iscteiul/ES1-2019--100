package quality;

import java.util.ArrayList;
import java.util.List;

import excelReader.FileRow;
import utils.DataBase;

/**
 * @author Alexandra Belo
 *
 * Class that evaluates iPlasma's and PMD's quality of defect detection of is_Long_Method.
 * For this evaluation counters are used for each quality indicator. 
 *
 */

public class ToolsQuality{	

	private List<FileRow> rows;

	/**
	 * RulesQuality constructor  
	 * @param DataBase object 
	 */

	public ToolsQuality(DataBase db) {
		this.rows = db.getExcel_file();  
	}

	public List<Integer> compare(String toolName){
		
		List<Integer> results = new ArrayList<Integer>();
		
		int DCI = 0;
		int DII = 0;
		int ADCI = 0;
		int ADII = 0;
		
		boolean aux = false;

		for(FileRow method : rows) {

			if(toolName.equals("PMD"))
				aux = method.isPMD();
			else if (toolName.equals("iPlasma"))
				aux = method.isiPlasma();

			if(method.isIs_Long_Method() == true) {
				if(aux == true)
					DCI ++;
				else 
					ADII ++;
			} else {
				if(aux == false) {
					ADCI ++;
				} else {
					DII ++;
				}
			}
		}
		
		results.add(DCI);
		results.add(DII);
		results.add(ADCI);
		results.add(ADII);
		
		return results;
	}	

}