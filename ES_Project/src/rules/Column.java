package rules;

import java.util.ArrayList;
import java.util.List;

import utils.Defect;

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
		this.array = new ArrayList<LineResult>();
		this.ruleName = ruleName;
		this.ruleType = ruleType;
	}	

	/**
	 * @return Defect
	 * 
	 * Retorna o valor do enumerado Defect
	 */
	public Defect getRuleType() {
		return ruleType;
	}

	/**
	 * @return Array com o objeto LineResult
	 * 
	 * Retorna então uma List com objetos do tipo LineResult
	 */
	public List<LineResult> getArray() {
		return array;
	}

	/**
	 * @return nome da regra
	 * 
	 * Com este método conseguimos extrair o nome da regra dada pelo utilizador
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param e
	 * 
	 * Adicionamos ao array um objeto do tipo LineResult
	 */
	public void addResult(LineResult e) {
		array.add(e);
	}
}