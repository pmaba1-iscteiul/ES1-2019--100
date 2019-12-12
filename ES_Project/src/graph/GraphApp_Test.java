package graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import excelReader.FileRow;
import rules.BuildRules;
import rules.LogicOperator;
import rules.Rule;
import rules.RulePart;
import userinterface.Defect;
import utils.DataBase;

public class GraphApp_Test {
	
	DataBase db = new DataBase("C:/Users/alexa/Documents/METI/1st semester/ESI/projeto/Long-Method.xlsx");
	GraphApp ga = new GraphApp(db);
	List<LogicOperator> lo = new ArrayList<LogicOperator>();
	List<RulePart> l1 = new ArrayList<RulePart>();
	List<RulePart> l2 = new ArrayList<RulePart>();
	List<FileRow> rows = db.getExcel_file();



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
		
		ga.createHistogramRules();
		assertEquals(1, ga.getComponentCount());
	}

	@Test
	public void testCreateHistogramTools() {
		ga.createHistogramTools();
		assertEquals(1, ga.getComponentCount());	
	}
	
	@Test
	public void testCreateDatasetTools() {
		CategoryDataset cd =  ga.createDatasetTools();
		assertEquals("org.jfree.data.category.DefaultCategoryDataset@a6c9beaf", cd.toString());
	}
	
	@Test
	public void testCreateDatasetRules() {
		CategoryDataset cd = ga.createDatasetRules();
		assertEquals("org.jfree.data.category.DefaultCategoryDataset@367", cd.toString());
	}
	
	@Test
	public void testCompareTools() {
		assertEquals(140, (int) ga.compareTools("PMD").get(0));
		assertEquals(0, (int) ga.compareTools("PMD").get(1));
		assertEquals(280, (int) ga.compareTools("PMD").get(2));
		assertEquals(0, (int) ga.compareTools("PMD").get(3));
		
		assertEquals(140, (int) ga.compareTools("iPlasma").get(0));
		assertEquals(18, (int) ga.compareTools("iPlasma").get(1));
		assertEquals(262, (int) ga.compareTools("iPlasma").get(2));
		assertEquals(0, (int) ga.compareTools("iPlasma").get(3));
		
		assertEquals(0, (int) ga.compareTools("ABC").get(0));
		assertEquals(0, (int) ga.compareTools("ABC").get(1));
		assertEquals(0, (int) ga.compareTools("ABC").get(2));
		assertEquals(0, (int) ga.compareTools("ABC").get(3));
		
	}

	@Test
	public void testCompareRules() {
		lo.add(LogicOperator.AND);	
		
		l1.add(new RulePart("LOC", 80, ">"));
		l1.add(new RulePart("CYCLO", 10, ">"));
		Rule obj = new Rule(l1, lo, "long method", Defect.is_long) ;
		BuildRules r = new BuildRules(obj, db);
		r.calculate();
				
		assertEquals(137, (int) ga.compareRules("long method").get(0));
		assertEquals(0, (int) ga.compareRules("long method").get(1));
		assertEquals(280, (int) ga.compareRules("long method").get(2));
		assertEquals(3, (int) ga.compareRules("long method").get(3));
		
		l2.add(new RulePart("ATFD", 4, ">"));
		l2.add(new RulePart("LAA", 0.42, "<"));
		Rule object = new Rule(l2, lo, "feature envy", Defect.is_feature_envy) ;
		BuildRules rules = new BuildRules(object, db);
		rules.calculate();
		
		assertEquals(112, (int) ga.compareRules("feature envy").get(0));
		assertEquals(0, (int) ga.compareRules("feature envy").get(1));
		assertEquals(306, (int) ga.compareRules("feature envy").get(2));
		assertEquals(2, (int) ga.compareRules("feature envy").get(3));
				
		rows.add(0, new FileRow(500, "", "", "", 0, 0, 0, 0, false, false, false, false));
		
		assertEquals(0, (int) ga.compareRules("feature envy").get(0));
		assertEquals(0, (int) ga.compareRules("feature envy").get(1));
		assertEquals(0, (int) ga.compareRules("feature envy").get(2));
		assertEquals(0, (int) ga.compareRules("feature envy").get(3));
	}

	@Test
	public void testAuxCompare() {
		assertEquals(0, (int)ga.auxCompare(true, true));
		assertEquals(1, (int)ga.auxCompare(false, true));
		assertEquals(2, (int)ga.auxCompare(false, false));
		assertEquals(3, (int)ga.auxCompare(true, false));
	}

}
