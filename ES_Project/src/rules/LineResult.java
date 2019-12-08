package rules;

/**
 * @author Francisco Raimundo
 * 
 * Objeto final, com o ID e o resultado desse ID
 *
 */
public class LineResult implements Serializable{

	int methodID;
	boolean result;
	
	
	public LineResult(int methodID, boolean result) {
		super();
		this.methodID = methodID;
		this.result = result;
	}


	/**
	 * @return ID
	 */
	public int getMethodID() {
		return methodID;
	}


	/**
	 * @param ID
	 */
	public void setMethodID(int methodID) {
		this.methodID = methodID;
	}


	/**
	 * @return resultado
	 */
	public boolean isResult() {
		return result;
	}


	/**
	 * @param resultado
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	
	
	
}
