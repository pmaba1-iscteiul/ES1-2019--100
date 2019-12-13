package rules;

import java.io.Serializable;

/**
 * @author Francisco Raimundo
 * 
 * Esta classe tem como objetivo construir um objeto para que 
 * possa ser mais fácil perceber qual a feature e o limite que 
 * o utilizador colocou .
 *
 */
public class RulePart{
	// Regra, parametro que é suposto usar, operador e limite

	private String metric;
	private double limit;
	private String operator;
	
	
	
	public RulePart(String metric, double limit, String operator) {
		super();
		this.metric = metric;
		this.limit = limit;
		this.operator = operator;
	}

	/**
	 * @return feature
	 */
	public String getFeature() {
		return metric;
	}
	
	/**
	 * @return metric
	 * 
	 * Retorna o tipo da métrica
	 */
	public String getMetric() {
		return metric;
	}

	/**
	 * @param metric
	 */
	public void setMetric(String metric) {
		this.metric=metric;
	}
	
	/**
	 * @return limit
	 * 
	 * Retorna o valor do limite 
	 */
	public double getLimit() {
		return limit;
	}
	
	/**
	 * @param limit
	 * Define o limite
	 */
	public void setLimit(double limit) {
		this.limit=limit;
	}

	/**
	 * @return operator
	 * 
	 * Retorna o operador que pode ser (<,>,<= ou ainda =>)
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return toString
	 * 
	 * Retorna string com informação contida no objeto RulePart
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return metric + " " + operator + " " + limit;
	}
}

