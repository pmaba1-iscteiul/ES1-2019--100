package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import graph.GraphApp;
import rules.BuildRules;
import rules.Rule;
import rules.RulePart;
import utils.DataBase;
import utils.Defect;
import utils.LogicOperator;

public class GraphApp_Test {
	
	DataBase db = new DataBase("D:/Computer_Files/Downloads/Long-Method.xlsx");
	GraphApp ga = new GraphApp(db);
	List<LogicOperator> lo = new ArrayList<LogicOperator>();
	List<RulePart> l1 = new ArrayList<RulePart>();
	List<RulePart> l2 = new ArrayList<RulePart>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateHistogramRules() {
		lo.add(LogicOperator.AND);	
		
		l1.add(new RulePart("LOC", 80, ">"));
		l1.add(new RulePart("CYCLO", 10, ">"));
		Rule obj = new Rule(l1, lo, "long method", Defect.is_long) ;
		BuildRules r = new BuildRules(obj, db);
		r.calculate();
		
		l2.add(new RulePart("ATFD", 4, ">"));
		l2.add(new RulePart("LAA", 0.42, "<"));
		Rule object = new Rule(l2, lo, "feature envy", Defect.is_feature_envy) ;
		BuildRules rules = new BuildRules(object, db);
		rules.calculate();
		
		ga.createHistogramRules();
		assertEquals(true, ga.open());
	}

	@Test
	public void testCreateHistogramTools() {
		ga.createHistogramTools();
		assertEquals(true, ga.open());	
	}
	
}
