package rules;

import java.util.ArrayList;
import java.util.List;

import userinterface.Defect;

/**
 * @author Francisco Raimundo
 * 
 * Coluna completa, em que temos o nome do método escolhido pelo utilizador e todos os resultados (true ou false) para todos os IDs
 *
 */
public class Column {

	private List<LineResult> array;
	private String ruleName;
	private Defect ruleType;

	public Column(String ruleName, Defect ruleType) {
		super();
		this.array = new ArrayList<LineResult>();
		this.ruleName = ruleName;
		this.ruleType = ruleType;
	}	
	
	public Defect getRuleType() {
		return ruleType;
	}
	
	/**
	 * @return Array com o objeto LineResult
	 */
	public List<LineResult> getArray() {
		return array;
	}
	
	/**
	 * @return nome da regra
	 */
	public String getRuleName() {
		return ruleName;
	}

	public void addResult(LineResult e) {
		array.add(e);
	}
}

