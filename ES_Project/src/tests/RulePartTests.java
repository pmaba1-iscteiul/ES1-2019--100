package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rules.RulePart;

public class RulePartTests {


	String metric = "LOC";
	double limit = 12.2;
	String operator = "<";
	
	RulePart rulePart = new RulePart(metric, limit, operator);
	
	@Test
	public void testRulePart() {
		assertEquals("<", rulePart.getOperator());
		assertNotEquals(">", rulePart.getOperator());
	}

	@Test
	public void testSetMetric() {
		rulePart.setMetric("LAA");
		assertEquals("LAA", rulePart.getMetric());
		assertNotEquals("LOC", rulePart.getMetric());
	}

	@Test
	public void testGetLimit() {
		assertNotEquals(11, rulePart.getLimit());
	}

	@Test
	public void testSetLimit() {
		rulePart.setLimit(13.5);
		assertEquals(13.5, rulePart.getLimit(), 0.001);
		assertNotEquals(10, rulePart.getLimit(), 0.001);
	}

	@Test
	public void testGetOperator() {
		assertEquals("<", rulePart.getOperator());
		assertNotEquals(">", rulePart.getOperator());
	}

	@Test
	public void testSetOperator() {
		rulePart.setOperator("<=");
		assertEquals("<=", rulePart.getOperator());
		assertNotEquals(">", rulePart.getOperator());
	}

}
