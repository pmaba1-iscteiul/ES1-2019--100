package excelReader;

public class Method {

	private final int MethodID;
	private final String Package;
	private final String ClassName;
	private final String Method;
	private final int LOC;
	private final int CYCLO;
	private final int ATFD;
	private final double LAA;
	private final boolean is_Long_Method;
	private final boolean PMD;
	private final boolean iPlasma;
	private final boolean is_Feature_Envy;

	public Method(int methodID, String package1, String className, String method, int lOC, int cYCLO, int aTFD,
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

	public int getMethodID() {
		return MethodID;
	}

	public String getPackage() {
		return Package;
	}

	public String getClassName() {
		return ClassName;
	}

	public String getMethod() {
		return Method;
	}

	public int getLOC() {
		return LOC;
	}

	public int getCYCLO() {
		return CYCLO;
	}

	public int getATFD() {
		return ATFD;
	}

	public double getLAA() {
		return LAA;
	}

	public boolean isIs_Long_Method() {
		return is_Long_Method;
	}

	public boolean isPMD() {
		return PMD;
	}

	public boolean isiPlasma() {
		return iPlasma;
	}

	public boolean isIs_Feature_Envy() {
		return is_Feature_Envy;
	}



}