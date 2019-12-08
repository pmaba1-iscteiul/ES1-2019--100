package rules;

import java.util.ArrayList;

/**
 * @author Francisco Raimundo
 * 
 * Coluna completa, em que temos o nome do método escolhido pelo utilizador e todos os resultados (true ou false) para todos os IDs
 *
 */
public class Column implements Serializable {

	ArrayList<LineResult> array;
	String ruleName;
	public Column(ArrayList<LineResult> array, String ruleName) {
		super();
		this.array = array;
		this.ruleName = ruleName;
	}
	/**
	 * @return Array com o objeto LineResult
	 */
	public ArrayList<LineResult> getArray() {
		return array;
	}
	/**
	 * @param array com o objeto LineResult
	 */
	public void setArray(ArrayList<LineResult> array) {
		this.array = array;
	}
	/**
	 * @return nome da regra
	 */
	public String getRuleName() {
		return ruleName;
	}
	/**
	 * @param nome da regra
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}	
}

