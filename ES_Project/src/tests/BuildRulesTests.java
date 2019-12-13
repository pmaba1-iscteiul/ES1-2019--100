package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import excelReader.FileRow;
import rules.BuildRules;
import rules.Column;
import rules.Rule;
import rules.RulePart;
import utils.DataBase;
import utils.Defect;
import utils.LogicOperator;

public class BuildRulesTests {
	//private DataBase data = new DataBase("C:\Long-Method.xlsx");
	
	List<RulePart> listObjectsVO = new ArrayList<RulePart>();
	ArrayList<LogicOperator> listOperators = new ArrayList<LogicOperator>();
	String ruleName = "batata";
	Defect ruleType = Defect.is_feature_envy;
	List<Boolean> arrayIntermedio = new ArrayList<Boolean>();
	Rule objectRuleVO = new Rule(listObjectsVO, listOperators, ruleName, ruleType);
	DataBase data;
	
	String metric="LOC";
	double limit=10;
	String operator=">";
	
	RulePart rulePart = new RulePart(metric, limit, operator);
	
	BuildRules buildRules = new BuildRules(objectRuleVO, data);
	
	
	@Test
	public void testCalculate() {
		
		Column column = new Column(objectRuleVO.getRuleName(), objectRuleVO.getRuleType());
		
		FileRow fileRow = new FileRow(1,"package","name","method",10,11,12,13,true,false,false,true);
		FileRow fileRow2 = new FileRow(1,"package","name","method",15,11,12,13,true,false,false,true);
	}

	@Test
	public void testContasComOperadoresLogicos() {
		arrayIntermedio.add(true);
		arrayIntermedio.add(false);
		listOperators.add(LogicOperator.AND);
		boolean result = buildRules.contasComOperadoresLogicos(listOperators, arrayIntermedio);
		assertEquals(false, result);
		arrayIntermedio.clear();
		listOperators.clear();
		
	}

	@Test
	public void testCalculaBoolean() {
		arrayIntermedio.add(true);
		arrayIntermedio.add(true);
		listOperators.add(LogicOperator.AND);
		boolean result = buildRules.contasComOperadoresLogicos(listOperators, arrayIntermedio);
		assertEquals(true, result);
		arrayIntermedio.clear();
		listOperators.clear();
		
		arrayIntermedio.add(false);
		arrayIntermedio.add(false);
		listOperators.add(LogicOperator.AND);
		boolean result1 = buildRules.contasComOperadoresLogicos(listOperators, arrayIntermedio);
		assertEquals(false, result1);
		arrayIntermedio.clear();
		listOperators.clear();
		
		arrayIntermedio.add(false);
		arrayIntermedio.add(false);
		listOperators.add(LogicOperator.OR);
		boolean result2 = buildRules.contasComOperadoresLogicos(listOperators, arrayIntermedio);
		assertEquals(false, result2);
		arrayIntermedio.clear();
		listOperators.clear();
		
		arrayIntermedio.add(true);
		arrayIntermedio.add(true);
		listOperators.add(LogicOperator.OR);
		boolean result3 = buildRules.contasComOperadoresLogicos(listOperators, arrayIntermedio);
		assertEquals(true, result3);
		arrayIntermedio.clear();
		listOperators.clear();
		
		arrayIntermedio.add(true);
		arrayIntermedio.add(false);
		listOperators.add(LogicOperator.OR);
		boolean result4 = buildRules.contasComOperadoresLogicos(listOperators, arrayIntermedio);
		assertEquals(true, result4);
		arrayIntermedio.clear();
		listOperators.clear();
	}

	@Test
	public void testValueRulePart() {
		FileRow fileRow = new FileRow(1,"package","name","method",10,11,12,13,true,false,false,true);
		FileRow fileRow2 = new FileRow(1,"package","name","method",15,11,12,13,true,false,false,true);
		FileRow fileRow3 = new FileRow(1,"package","name","method",5,11,12,13,true,false,false,true);
		FileRow fileRow4 = new FileRow(1,"package","name","method",1,11,12,13,true,false,false,true);
		
		String metric="LOC";
		String metric2="CYCLO";
		String metric3="ATFD";
		String metric4="LAA";
		String metric5="olaaaa";
		double limit5=15; 
		double limit=10;
		double limit2=11;
		double limit3=12;
		double limit4=13;
		String operator=">";
		String operator2=">=";
		String operator3="<";
		String operator4="<=";
		
		
		RulePart rulePart = new RulePart(metric, limit, operator);
		RulePart rulePart2 = new RulePart(metric, limit, operator2);
		RulePart rulePart3 = new RulePart(metric, limit, operator3);
		RulePart rulePart4 = new RulePart(metric, limit, operator4);
		RulePart rulePart5 = new RulePart(metric, limit5, operator3);
		
		assertEquals(false, buildRules.valueRulePart(fileRow, rulePart));
		assertEquals(true, buildRules.valueRulePart(fileRow, rulePart2));
		assertEquals(false, buildRules.valueRulePart(fileRow, rulePart3));
		assertEquals(true, buildRules.valueRulePart(fileRow, rulePart4));
		
		assertEquals(true, buildRules.valueRulePart(fileRow, rulePart5));
		assertEquals(false, buildRules.valueRulePart(fileRow2, rulePart4));
		assertEquals(true, buildRules.valueRulePart(fileRow2, rulePart));
		assertEquals(false, buildRules.valueRulePart(fileRow3, rulePart2));
		
	}

	@Test
	public void testGetLimiteDaLinha() {
		FileRow fileRow = new FileRow(1,"package","name","method",10,11,12,13,true,false,false,true);
		
		String metric="LOC";
		String metric2="CYCLO";
		String metric3="ATFD";
		String metric4="LAA";
		String metric5="olaaaa";
		double limit=10;
		double limit2=11;
		double limit3=12;
		double limit4=13;
		String operator=">";
		
		RulePart rulePart = new RulePart(metric, limit, operator);
		RulePart rulePart2 = new RulePart(metric2, limit2, operator);
		RulePart rulePart3 = new RulePart(metric3, limit3, operator);
		RulePart rulePart4 = new RulePart(metric4, limit4, operator);
		RulePart rulePart5 = new RulePart(metric5, limit, operator);
		assertEquals(limit, buildRules.getLimiteDaLinha(fileRow, rulePart), 0.001);
		assertEquals(limit2, buildRules.getLimiteDaLinha(fileRow, rulePart2), 0.001);
		assertEquals(limit3, buildRules.getLimiteDaLinha(fileRow, rulePart3), 0.001);
		assertEquals(limit4, buildRules.getLimiteDaLinha(fileRow, rulePart4), 0.001);
		
		assertNotEquals(limit, buildRules.getLimiteDaLinha(fileRow, rulePart4), 0.001);
		assertEquals(-1.0, buildRules.getLimiteDaLinha(fileRow, rulePart5), 0.001);
	}

}
