package excelReader;
/**
 * 
 * The aim of this class is to create an object containing all of the row's columns values
 * 
 * @author Pedro Batoca
 *
 */

public class FileRow {

	private final int MethodID;
	private final String Package;
	private final String ClassName;
	private final String Method;
	private final double LOC;
	private final double CYCLO;
	private final double ATFD;
	private final double LAA;
	private final boolean is_Long_Method;
	private final boolean PMD;
	private final boolean iPlasma;
	private final boolean is_Feature_Envy;

	
	
	/**
	 * 
	 * This constructor generates a FileRow object with the attributes corresponding to the values of each column in the excel file
	 * 
	 * @param methodID
	 * @param package1
	 * @param className
	 * @param method
	 * @param lOC
	 * @param cYCLO
	 * @param aTFD
	 * @param lAA
	 * @param is_Long_Method
	 * @param pMD
	 * @param iPlasma
	 * @param is_Feature_Envy
	 * 
	 */
	
	public FileRow(int methodID, String package1, String className, String method, double lOC, double cYCLO, double aTFD,
			double lAA, boolean is_Long_Method, boolean pMD, boolean iPlasma, boolean is_Feature_Envy) {

		MethodID = methodID;
		Package = package1;
		ClassName = className;
		Method = method;
		LOC = lOC;
		CYCLO = cYCLO;
		ATFD = aTFD;
		LAA = lAA;
		this.is_Long_Method = is_Long_Method;
		PMD = pMD;
		this.iPlasma = iPlasma;
		this.is_Feature_Envy = is_Feature_Envy;
	}

	/**
	 * 
	 * Returns the Method ID
	 * 
	 * @return MethodID
	 * 
	 */
	
	public int getMethodID() {
		return MethodID;
	}

	/**
	 * 
	 * Returns the Package Name
	 * 
	 * @return String "Package"
	 *
	 */
	
	public String getPackage() {
		return Package;
	}


	/**
	 * 
	 * Returns the Class Name
	 * 
	 * @return String ClassName
	 * 
	 */
	
	
	public String getClassName() {
		return ClassName;
	}

	/**
	 * 
	 * Returns the Method's Name
	 * 
	 * @return String Method	 
	 *
	 */
	
	public String getMethod() {
		return Method;
	}

	/**
	 * 
	 * Returns the value of the LOC attribute
	 * 
	 * @return double LOC
	 * 
	 */
	
	public double getLOC() {
		return LOC;
	}

	/**
	 * 
	 * Returns the value of the CYCLO attribute
	 * 
	 * @return double CYCLO
	 * 
	 */
	
	public double getCYCLO() {
		return CYCLO;
	}

	/**
	 * 
	 * Returns the value of the ATFD attribute
	 * 
	 * @return double ATFD
	 * 
	 */
	
	public double getATFD() {
		return ATFD;
	}

	/**
	 * 
	 * Returns the value of the LAA attribute
	 * 
	 * @return double LAA
	 * 
	 */
	
	public double getLAA() {
		return LAA;
	}

	/**
	 * 
	 * Returns the value of the is_Long_Method attribute
	 * 
	 * @return boolean is_Long_Method
	 * 
	 */
	
	public boolean isIs_Long_Method() {
		return is_Long_Method;
	}

	/**
	 * 
	 * Returns the value of the PMD attribute
	 * 
	 * @return boolean PMD
	 * 
	 */
	
	public boolean isPMD() {
		return PMD;
	}

	/**
	 * 
	 * Returns the value of the iPlasma attribute
	 * 
	 * @return boolean iPlasma
	 * 
	 */
	
	public boolean isiPlasma() {
		return iPlasma;
	}

	/**
	 * 
	 * Returns the value of the is_Feature_Envy attribute
	 * 
	 * @return boolean is_Feature_Envy
	 * 
	 */
	
	public boolean isIs_Feature_Envy() {
		return is_Feature_Envy;
	}
	
	
	
	/**
	 * Returns all attributes for a FileRow object as a String
	 * 
	 * @return String row
	 * 
	 * 
	 */
	
	public String getRow() {
		String row = MethodID + ";" + Package + ";" + ClassName + ";" + Method + ";" + LOC + ";" + CYCLO + ";" + ATFD + ";" + LAA + ";" + is_Long_Method + ";" + PMD + ";" + iPlasma + ";" + is_Feature_Envy;  
		return row;
	}

}