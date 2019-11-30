package rules;

import java.util.ArrayList;
import java.util.Iterator;

import rules.Column;
import rules.LineResult;
import rules.Linha;
import rules.LogicOperator;
import rules.Method;
import rules.ObjectRuleVO;
import rules.ObjectVO;

/**
 * @author Francisco Raimundo
 *
 */

public class BuildRules {

	String auxFeature = "";
	double auxLimit = 0.0d;
	String auxOperator;
	ArrayList<ObjectVO> arrayObjectsVO;
	ArrayList<LogicOperator> arrayLogicOperators;
	ArrayList<Boolean> arrayIntermedio = new ArrayList<>();
	ArrayList<Boolean> arrayFinal = new ArrayList<>();
	ArrayList<Linha> arrayLinhas;

	public static void main(String[] args) {

		BuildRules buildRules = new BuildRules();

		Linha linha = new Linha();
		linha.setLOC(5);
		linha.setCYCLO(5);
		linha.setATFD(5);
		linha.setLAA(5);
		ArrayList<Linha> arrayLinhas = new ArrayList<>();
		arrayLinhas.add(linha);

		ObjectVO objectVO = new ObjectVO();
		objectVO.setMetric("LOC");
		objectVO.setLimit(6);
		objectVO.setOperator("<");

		ObjectVO objectVOO = new ObjectVO();
		objectVOO.setMetric("ATFD");
		objectVOO.setLimit(6);
		objectVOO.setOperator(">");

		ObjectVO objectVOOO = new ObjectVO();
		objectVOOO.setMetric("LAA");
		objectVOOO.setLimit(6);
		objectVOOO.setOperator("<");

		ArrayList<ObjectVO> arrayObjects = new ArrayList<>();
		arrayObjects.add(objectVO);
		arrayObjects.add(objectVOO);
		arrayObjects.add(objectVOOO);

		ObjectRuleVO objectRuleVO = new ObjectRuleVO();
		objectRuleVO.setListObjectsVO(arrayObjects);

		ArrayList<LogicOperator> arrayLogicsOperator = new ArrayList<>();
		arrayLogicsOperator.add(LogicOperator.AND);
		arrayLogicsOperator.add(LogicOperator.OR);

		objectRuleVO.setListOperators(arrayLogicsOperator);

		buildRules.getAndSetBoolean(objectRuleVO, arrayLinhas);

	}

	/**
	 * @param objectRuleVO
	 * @param linha
	 * 
	 * método principal em que para cada linha, vemos o resultado que tem de dar, consoantes
	 * as escolhas feitas pelo utilizador e é colocado num objeto final
	 * para ser comparado com as regras existentes no ficheiro
	 */
	public void getAndSetBoolean(ObjectRuleVO objectRuleVO, ArrayList<Method> linha) {

		boolean boleano = false;
		LineResult lineResult;
		Column column;
		
		for(Method l : linha) {

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
				System.out.println(boolean1);
				System.out.println(boolean2);
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
	private double getLimiteDaLinha(Linha l, ObjectVO a) {
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
