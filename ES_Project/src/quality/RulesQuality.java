package quality;

import java.util.ArrayList;
import java.util.List;
import rules.LineResult;
import rules.Column;
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
	private List<FileRow> rows;
	private List<Column> columns;

	/**
	 * RulesQuality constructor  
	 * @param DataBase object 
	 */
	public RulesQuality (DataBase db) {
		this.rows = db.getExcel_file();
		this.columns = db.getColumns();
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
	private void compare(String ruleName){
		List<LineResult> lineResults;
		for(Column col : columns) {
			if (col.getRuleName().equals(ruleName)) {
				if (col.getRuleType().equals("is_Long_Method")) {
					lineResults = col.getArray();
					for(FileRow method : rows) {
						for(LineResult line : lineResults) {
							if(method.getMethodID() == line.getMethodID()) {
								boolean result = line.isResult();
								if(method.isIs_Long_Method() == true) {
									if(result == true) {
										DCI ++;
									} else {
										ADII ++;
									}	
								} else {
									if(result == false) {
										ADCI ++;
									} else {
										DII ++;
									}	
								}
							}
						}
					}
				} else if (col.getRuleType().equals("is_Feature_Envy")) {
					lineResults = col.getArray();
					for(FileRow method : rows) {
						for(LineResult line : lineResults) {
							if(method.getMethodID() == line.getMethodID()) {
								boolean result = line.isResult();					
								if(method.isIs_Feature_Envy() == true) {
									if(result == true) {
										DCI ++;
									} else {
										ADII ++;
									}	
								} else {
									if(result == false) {
										ADCI ++;
									} else {
										DII ++;
									}	
								}
							}
						}
					}
				}
			}
		}
	}
}	