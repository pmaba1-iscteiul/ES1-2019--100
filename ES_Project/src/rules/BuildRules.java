package rules;

import java.util.ArrayList;
import java.util.List;

import excelReader.FileRow;
import utils.DataBase;
import utils.LogicOperator;

/**
 * @author Francisco Raimundo
 *
 *Classe principal em que vai linha a linha, comparar com a nova regra criada
 *pelo utilizador, vai chegar a um valor booleano, vai ter em conta os 
 *operadores lógicos escolhidos pelo utilizador e no final vai chegar 
 *à conclusão que é verdadeira ou falsa a atual linha do ficheiro isto mais 
 *uma vez, tendo em conta uma regra criada pelo utilizador
 */

public class BuildRules {

	private Rule objectRuleVO;
	private DataBase data;

	public BuildRules(Rule objectRuleVO, DataBase data) {
		this.objectRuleVO=objectRuleVO;
		this.data=data;
	}


	/**
	 * @param objectRuleVO
	 * @param linha
	 * 
	 * método principal em que para cada linha, vemos o resultado que tem de dar, consoantes
	 * as escolhas feitas pelo utilizador e é colocado num objeto final
	 * para ser comparado com as regras existentes no ficheiro
	 */
	public void calculate() {

		Column column = new Column(objectRuleVO.getRuleName(), objectRuleVO.getRuleType());

		for(FileRow l : data.getExcel_file()) {

			List<RulePart> arrayObjectsVO = objectRuleVO.getRulePart();
			List<LogicOperator> arrayLogicOperators = objectRuleVO.getListLogicOperators();
			List<Boolean> arrayIntermedio = new ArrayList<>();

			for (RulePart a : arrayObjectsVO) 
				arrayIntermedio.add(valueRulePart(l, a));

			column.addResult(new LineResult(l.getMethodID(), contasComOperadoresLogicos(arrayLogicOperators, arrayIntermedio)));

		}

		data.addColumn(column);
	}

	/**
	 * @param Array com os operadores lógicos
	 * @return boolean
	 * 
	 * método para calcular o boolean da regra, dependendo dos limites 
	 * escolhidos pelo utilizador
	 */
	public boolean contasComOperadoresLogicos(List<LogicOperator> arrayLogicOperators, List<Boolean> arrayIntermedio ) {
		//Calcula o valor lógico do if


		boolean intermedio = arrayIntermedio.get(0);

		for(int i = 1; i < arrayIntermedio.size(); i++)
			intermedio = calculaBoolean(intermedio, arrayIntermedio.get(i), arrayLogicOperators.get(i-1));

		return intermedio; 
	}

	/**
	 * @param boolean1
	 * @param boolean2
	 * @param operador lógico
	 * @return boolean final
	 * 
	 * faz a conta, do boolean que resulta de 2 boolean com um operador lógico
	 */
	public boolean calculaBoolean(Boolean boolean1, Boolean boolean2, LogicOperator logicOperator) {
		//Primeira Regra, Segunda Regra e Operador Logico
		boolean auxBoolean=false;
		if(logicOperator.equals(LogicOperator.AND)) {
			if(boolean1.equals(true) && boolean2.equals(true)) {
				auxBoolean=true;
			} else {
				auxBoolean=false;
			}
		} else {
			if(boolean1.equals(false) && boolean2.equals(false)) {
				auxBoolean=false;
			}
			else {
				auxBoolean=true;
			}
		}
		return auxBoolean;
	}


	/**
	 * @param linha
	 * @param ObjectVO
	 * 
	 * Verifica se o utilizador escolheu < ou > e verifica com o valor da linha
	 * se dá true ou false .
	 * No final coloca tudo num array para poder ser usado posteriormente
	 */
	public boolean valueRulePart(FileRow linha, RulePart a) {
		//Criar enumerado para operadores numericos
		double limiteNaLinha = getLimiteDaLinha(linha, a);

		if (a.getOperator().equals("<")) {

			if (limiteNaLinha < a.getLimit())
				return true;
			else 
				return false;
		}else if (a.getOperator().equals("<=")) {

			if(limiteNaLinha <= a.getLimit()) 
				return true;
			else 
				return false;
		} else if (a.getOperator().equals(">")) {

			if(limiteNaLinha > a.getLimit()) 
				return true;
			else 
				return false;

		} else {

			if(limiteNaLinha >= a.getLimit()) 
				return true;
			else 
				return false;
		}

	}

	/**
	 * @param linha
	 * @param ObjectVO
	 * @return double
	 * 
	 * Coloca no double o valor dependendo de qual feature foi escolhida
	 */
	public double getLimiteDaLinha(FileRow l, RulePart a) {
		try {
			switch(a.getMetric()) {
			case"LOC":
				return l.getLOC();
			case"CYCLO":
				return l.getCYCLO();
			case"ATFD":
				return l.getATFD();
			case"LAA":
				return l.getLAA();
			}
		} catch (Exception e) {
			e.getMessage();
		} 
		return -1.0;
	}

}