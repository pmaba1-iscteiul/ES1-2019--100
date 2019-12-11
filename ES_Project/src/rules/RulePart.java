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
public class RulePart implements Serializable{
	// Regra, parametro que é suposto usar, operador e limite

	private static final long serialVersionUID = 8929973800902868640L;
	String metric;
	double limit;
	String operator;
	
	
	
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
	 * @param feature
	 */
	public void setMetric(String metric) {
		this.metric=metric;
	}
	
	/**
	 * @return limit
	 */
	public double getLimit() {
		return limit;
	}
	
	/**
	 * @param limit
	 */
	public void setLimit(double limit) {
		this.limit=limit;
	}

	/**
	 * @return operator
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return metric + " " + operator + " " + limit;
	}
	
	
		
}

