package quality;

import java.util.ArrayList;
import excelReader.ExcelReader;
import excelReader.Method;

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

	public ToolsQuality() { //objeto
		this.rows = ; //as rows do excel 
		this.compare();
	}

	private void compare(){
		for(Method m : rows) {
			if(m.is_Long_Method == true) {
				if(m.PMD.equal(true)) {
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