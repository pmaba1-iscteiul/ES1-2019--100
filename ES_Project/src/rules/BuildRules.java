package rules;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Francisco Raimundo
 *
 *Classe criada para então implementar a questão de ser true ou false
 *em funcionamento dos limites de cada linha do excel e das regras criadas
 *pelo utilizador.
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

	public void getAndSetBoolean(ObjectRuleVO objectRuleVO, ArrayList<Linha> linha) {
		// for() {//percorre as linhas do array do batoca (excel)

		for(Linha l : linha) {

			arrayObjectsVO = objectRuleVO.getListObjectsVO();
			arrayLogicOperators = objectRuleVO.getListLogicOperators();

			for (ObjectVO a : arrayObjectsVO) {
				colocaNoArrayBooleans(l, a);
			}
			l.setDepoisDasRegras(contasComOperadoresLogicos(arrayLogicOperators));

			arrayFinal.clear();
			arrayIntermedio.clear();
		}

	}

	private boolean contasComOperadoresLogicos(ArrayList<LogicOperator> arrayLogicOperators) {

		int i = 0;
		boolean auxBoolean = false;
		for(int iterador = 0; iterador!=arrayLogicOperators.size(); iterador++) {
			if(i==0) {
				System.out.println(arrayLogicOperators.get(iterador));
				if(arrayLogicOperators.get(iterador).equals(LogicOperator.AND)) {
					if(arrayIntermedio.get(i).equals(true) && arrayIntermedio.get(i+1).equals(true)) {
						System.out.println(arrayIntermedio.get(i));
						System.out.println(arrayIntermedio.get(i+1));
						auxBoolean=true;
					} else {
						auxBoolean=false;
					}
				} else {
					if(arrayIntermedio.get(i).equals(false) && arrayIntermedio.get(i+1).equals(false)) {
						auxBoolean=false;
					}
					else {
						auxBoolean=true;
					}
				}
			} else {
			if(arrayLogicOperators.get(iterador).equals(LogicOperator.AND)) {
				if(auxBoolean==true && arrayIntermedio.get(i+1).equals(true)) {
					auxBoolean=true;
				} else {
					auxBoolean=false;
				}
			} else {
				if(arrayIntermedio.get(i).equals(false) && arrayIntermedio.get(i+1).equals(false)) {
					auxBoolean=false;
				}
				else {
					auxBoolean=true;
				}
			}
		}
			//System.out.printf("Posição %d- %s\n", i, iterator.next());
			i++;
		}

		return auxBoolean;
}

	private String extracted() {
		return "AND";
	}

public void colocaNoArrayBooleans(Linha linha, ObjectVO a) {

	auxFeature = a.getFeature();
	auxLimit = a.getLimit();
	auxOperator = a.getOperator();

	switch (auxFeature) {
	case "LOC":
		// getLOC() -> guardo o valor do LOC do excel e vejo com o
		// operador (< ou >) se dá true ou false

		if (auxOperator.equals("<")) {

			if (linha.getLOC() < auxLimit) {
				arrayIntermedio.add(true);
			} else {
				arrayIntermedio.add(false);
			}
		} else {
			if(linha.getLOC() > auxLimit) {
				arrayIntermedio.add(true);
			} else {
				arrayIntermedio.add(false);
			}
		}
		break;

	case "CYCLO":
		// getCYCLO() -> guardo o valor do CYCLO do excel e vejo com o
		// operador (< ou >) se dá true ou false
		if(auxOperator.equals("<")) {

			if( linha.getCYCLO() < auxLimit ) {
				arrayIntermedio.add(true);
			} else {
				arrayIntermedio.add(false);
			}	
		}  else {
			if(linha.getLOC() > auxLimit) {
				arrayIntermedio.add(true);
			} else {
				arrayIntermedio.add(false);
			}
		}
		break;

	case "ATFD":
		// getATFD() -> guardo o valor do ATFD do excel e vejo com o
		// operador (< ou >) se dá true ou false
		if(auxOperator.equals("<")) {

			if( linha.getATFD() < auxLimit ) {
				arrayIntermedio.add(true);
			} else {
				arrayIntermedio.add(false);
			}
		}  else {
			if(linha.getLOC() > auxLimit) {
				arrayIntermedio.add(true);
			} else {
				arrayIntermedio.add(false);
			}
		}
		break;

	case "LAA":
		// getLAA() -> guardo o valor do LAA do excel e vejo com o
		// operador (< ou >) se dá true ou false
		if(auxOperator.equals("<")) {

			if( linha.getLAA() < auxLimit ) {
				arrayIntermedio.add(true);
			} else {
				arrayIntermedio.add(false);
			}	
		} 
		break;
	}

}

}
