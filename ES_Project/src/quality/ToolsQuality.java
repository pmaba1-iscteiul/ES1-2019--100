package quality;

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

	private int PMD_DCI = 0;
	private int PMD_DII = 0;
	private int PMD_ADCI = 0;
	private int PMD_ADII = 0;
	private int iPlasma_DCI = 0;
	private int iPlasma_DII = 0;
	private int iPlasma_ADCI = 0;
	private int iPlasma_ADII = 0;
	private List<FileRow> rows;

	/**
	 * RulesQuality constructor  
	 * @param DataBase object 
	 */

	public ToolsQuality(DataBase db) {
		this.rows = db.getExcel_file();  
		this.compare();
	}

	/**
	 * Returns the number of correctly identified defects by the tool PMD
	 * @return the PMD_DCI counter
	 */

	public int getPMD_DCI() {
		return PMD_DCI;
	}

	/**
	 * Returns the number of incorrectly identified defects by the tool PMD
	 * @return the PMD_DII counter
	 */

	public int getPMD_DII() {
		return PMD_DII;
	}

	/**
	 * Returns the number of correctly identified absence of defects by the tool PMD
	 * @return the PMD_ADCI counter
	 */
	public int getPMD_ADCI() {
		return PMD_ADCI;
	}

	/**
	 * Returns the number of incorrectly identified absence of defects by the tool PMD
	 * @return the PMD_ADII counter
	 */

	public int getPMD_ADII() {
		return PMD_ADII;
	}

	/**
	 * Returns the number of correctly identified defects by the tool iPlasma
	 * @return the PMD_DCI counter
	 */

	public int getiPlasma_DCI() {
		return iPlasma_DCI;
	}

	/**
	 * Returns the number of incorrectly identified defects by the tool iPlasma
	 * @return the PMD_DII counter
	 */

	public int getiPlasma_DII() {
		return iPlasma_DII;
	}

	/**
	 * Returns the number of correctly identified absence of defects by the tool iPlasma
	 * @return the PMD_ADCI counter
	 */

	public int getiPlasma_ADCI() {
		return iPlasma_ADCI;
	}

	/**
	 * Returns the number of incorrectly identified absence of defects by the tool iPlasma
	 * @return the PMD_ADII counter
	 */

	public int getiPlasma_ADII() {
		return iPlasma_ADII;
	}

	private void compare(){
		for(FileRow method : rows) {
			if(method.isIs_Long_Method() == true) {
				if(method.isPMD() == true) {
					PMD_DCI ++;
				} else {
					PMD_ADII ++;
				}
				if(method.isiPlasma() == true) {
					iPlasma_DCI ++;
				} else {
					iPlasma_ADII ++;
				}	
			} else {
				if(method.isPMD() == false) {
					PMD_ADCI ++;
				} else {
					PMD_DII ++;
				}
				if(method.isiPlasma() == false) {
					iPlasma_ADCI ++;
				} else {
					iPlasma_DII ++;
				}	
			}
		}
	}	

	public static void main(String[] args) {
		DataBase db = new DataBase("C:/Users/alexa/Documents/METI/ESI/projeto/Long-Method.xlsx");
		//System.out.println(db.getExcel_file());
		ToolsQuality tq = new ToolsQuality(db);
		System.out.println("PDM:");
		System.out.println("DCI =" + tq.getPMD_DCI());
		System.out.println("DII =" + tq.getPMD_DII());
		System.out.println("ADCI =" + tq.getPMD_ADCI());
		System.out.println("ADII =" + tq.getPMD_ADII());
		System.out.println("iPlasma:");
		System.out.println("DCI =" + tq.getiPlasma_DCI());
		System.out.println("DII =" + tq.getiPlasma_DII());
		System.out.println("ADCI =" + tq.getiPlasma_ADCI());
		System.out.println("ADII =" + tq.getiPlasma_ADII());
		
	}
}