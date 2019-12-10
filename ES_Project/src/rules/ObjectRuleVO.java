package rules;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Francisco Raimundo
 *
 *Objeto para ser passado tudo de uma só vez, contendo então
 *uma lista de todas as métricas, limites associados a essa métrica
 *e também o sinal escolhido pelo utilizador (<,>)
 *E também temos a lista com os operadores lógicos todos escolhidos pelo 
 *utilizador (pela ordem escolhida) .
 */
public class ObjectRuleVO implements Serializable{

	ArrayList<ObjectVO> listObjectsVO;
	ArrayList<LogicOperator> listOperators;
	String ruleName;
	
	/**
	 * @return Lista de ObjectVO
	 */
	public ArrayList<ObjectVO> getListObjectsVO() {
		return listObjectsVO;
	}
	/**
	 * @param lista de ObjectVO
	 */
	public void setListObjectsVO(ArrayList<ObjectVO> listObjectsVO) {
		this.listObjectsVO = listObjectsVO;
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
	/**
	 * @return array com os operadores lógicos
	 */
	public ArrayList<LogicOperator> getListLogicOperators() {
		return listOperators;
	}
	/**
	 * @param array com os operadores lógicos
	 */
	public void setListOperators(ArrayList<LogicOperator> listOperators) {
		this.listOperators = listOperators;
	}
	
	
	
}
