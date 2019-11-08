package quality;

import java.util.ArrayList;
import excelReader.ExcelReader;
import excelReader.Method;

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
	private final ArrayList<Method> rows;
	
	/**
	 * RulesQuality constructor  
	 * @param Object from somewhere else! /* objeto 
	 */
	
	public ToolsQuality(/* objeto */) {
		this.rows = /* as linhas do excel */new ArrayList<Method>();  
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
		for(Method m : rows) {
			if(m.is_Long_Method == true) {
				if(m.PMD.equal == true) {
					PMD_DCI ++;
				} else {
					PMD_ADII ++;
				}
				if(m.iPlasma == true) {
					iPlasma_DCI ++;
				} else {
					iPlasma_ADII ++;
				}	
			} else {
				if(m.PMD == false) {
					PMD_ADCI ++;
				} else {
					PMD_DII ++;
				}
				if(m.iPlasma == false) {
					iPlasma_ADCI ++;
				} else {
					iPlasma_DII ++;
				}	
			}
		}
	}	
}