package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rules.Rule;
import rules.RulePart;
import utils.Defect;
import utils.LogicOperator;

public class RuleTests {

	private List<RulePart> listObjectsVO = new ArrayList<RulePart>();
	private ArrayList<LogicOperator> listOperators = new ArrayList<LogicOperator>();
	private String ruleName = "batata";
	private Defect ruleType = Defect.is_feature_envy;
	
	Rule rule = new Rule(listObjectsVO, listOperators, ruleName, ruleType);
	
	RulePart rulePart = new RulePart("LOC", 11.2, "<");
	
	
	@Test
	public void testRule() {
		listObjectsVO.add(rulePart);
		listOperators.add(LogicOperator.AND);
		assertEquals("batata",rule.getRuleName());
		assertNotEquals("batato", rule.getRuleName());
	}

	@Test
	public void testGetRulePart() {
		listObjectsVO.add(rulePart);
		assertEquals(listObjectsVO, rule.getRulePart());
	}

	@Test
	public void testSetRulePart() {
		ArrayList<RulePart> listObjectsVO2 = new ArrayList<>();
		listObjectsVO2.add(rulePart);
		rule.setRulePart(listObjectsVO2);
		assertEquals(rulePart, rule.getRulePart().get(0));
	}

	@Test
	public void testGetRuleName() {
		assertEquals("batata",rule.getRuleName());
		assertNotEquals("manel", rule.getRuleName());
	}

	@Test
	public void testGetRuleType() {
		assertEquals(Defect.is_feature_envy,rule.getRuleType());
		assertNotEquals(Defect.is_long, rule.getRuleType());
	}

	@Test
	public void testGetListLogicOperators() {
		listOperators.clear();
		listOperators.add(LogicOperator.OR);
		assertEquals(listOperators,rule.getListLogicOperators());
	}

	@Test
	public void testSetListOperators() {
		listOperators.clear();
		listOperators.add(LogicOperator.OR);
		rule.setListOperators(listOperators);
		assertEquals(listOperators,rule.getListLogicOperators());
	}

}
