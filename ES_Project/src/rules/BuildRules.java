package rules;

import java.util.ArrayList;
import utils.DataBase;
import java.util.Iterator;

import rules.Column;
import rules.LineResult;
import rules.Linha;
import rules.LogicOperator;
import rules.ObjectRuleVO;
import rules.ObjectVO;

/**
 * @author Francisco Raimundo
 *
 */

public class BuildRules {
	
	private ObjectRuleVO objectRuleVO;
	private String auxFeature = "";
	private double auxLimit = 0.0d;
	private String auxOperator;
	private ArrayList<ObjectVO> arrayObjectsVO;
	private ArrayList<LogicOperator> arrayLogicOperators;
	private ArrayList<Boolean> arrayIntermedio = new ArrayList<>();
	private ArrayList<Boolean> arrayFinal = new ArrayList<>();
	private ArrayList<Linha> arrayLinhas;
	private DataBase data;
	
	public BuildRules(ObjectRuleVO objectRuleVO, DataBase data) {
		this.objectRuleVO=objectRuleVO;
		this.data=data;
		arrayLinhas = data.getExcel_file();
		getAndSetBoolean();
	}
	

	/**
	 * @param objectRuleVO
	 * @param linha
	 * 
	 * método principal em que para cada linha, vemos o resultado que tem de dar, consoantes
	 * as escolhas feitas pelo utilizador e é colocado num objeto final
	 * para ser comparado com as regras existentes no ficheiro
	 */
	public void getAndSetBoolean() {

		boolean boleano = false;
		LineResult lineResult;
		Column column;
		
		for(FileRow l : arrayLinhas) {

			arrayObjectsVO = objectRuleVO.getListObjectsVO();
			arrayLogicOperators = objectRuleVO.getListLogicOperators();

			for (ObjectVO a : arrayObjectsVO) {
				colocaNoArrayBooleans(l, a);
			}
			boleano = contasComOperadoresLogicos(arrayLogicOperators);
			
			lineResult.set(l.getMethodID(), boleano);
			arrayLineResult.add(lineResult);
			
			arrayIntermedio.clear();
		}
		column.setArray(arrayLineResult);
		column.setRuleName(objectRuleVO.getRuleName());
		data.addColumn(column);
		arrayLineResult.clear();
	}

	/**
	 * @param Array com os operadores lógicos
	 * @return boolean
	 * 
	 * método para calcular o boolean da regra, dependendo dos limites 
	 * escolhidos pelo utilizador
	 */
	private boolean contasComOperadoresLogicos(ArrayList<LogicOperator> arrayLogicOperators) {

		boolean auxBooleanIntermedio=false;
		boolean auxBoolean=false;

		for(int a=1; a != arrayLogicOperators.size()+1; a=a+1) {
			if(arrayLogicOperators.size()==0) {
				auxBoolean=arrayIntermedio.get(a);
			}
			auxBooleanIntermedio=arrayIntermedio.get(a);
			auxBoolean=calculaBoolean(auxBooleanIntermedio, arrayIntermedio.get(a), arrayLogicOperators.get(a-1));
			auxBooleanIntermedio=auxBoolean;
		}
		return auxBoolean;
	}

	/**
	 * @param boolean1
	 * @param boolean2
	 * @param operador lógico
	 * @return boolean final
	 * 
	 * faz a conta, do boolean que resulta de 2 boolean com um operador lógico
	 */
	private boolean calculaBoolean(Boolean boolean1, Boolean boolean2, LogicOperator logicOperator) {
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
	public void colocaNoArrayBooleans(Linha linha, ObjectVO a) {

		double limiteNaLinha = getLimiteDaLinha(linha, a);
		
			if (a.getOperator().equals("<")) {
				if (limiteNaLinha < a.getLimit()) {
					arrayIntermedio.add(true);
				} else {
					arrayIntermedio.add(false);
				}
			} else {
				if(limiteNaLinha > a.getLimit()) {
					arrayIntermedio.add(true);
				} else {
					arrayIntermedio.add(false);
				}
			}
			
	}

	/**
	 * @param linha
	 * @param ObjectVO
	 * @return double
	 * 
	 * Coloca no double o valor dependendo de qual feature foi escolhida
	 */
	private double getLimiteDaLinha(FileRow l, ObjectVO a) {
		Double auxDouble = 0.0;
		try {
			switch(a.getFeature()) {
			case"LOC":
				auxDouble = l.getLOC();
				break;
			case"CYCLO":
				auxDouble = l.getCYCLO();
				break;
			case"ATFD":
				auxDouble = l.getATFD();
				break;
			case"LAA":
				auxDouble = l.getLAA();
				break;
		}
			
		} catch (Exception e) {
			e.getMessage();
		} 
		return auxDouble;
	}

}
