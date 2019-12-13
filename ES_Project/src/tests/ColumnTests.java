package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import rules.Column;
import rules.LineResult;
import utils.Defect;

public class ColumnTests {
	
	private List<LineResult> array = new ArrayList<LineResult>();
	private String ruleName = "regra do francisco";
	private Defect ruleType = Defect.is_long;
	
	LineResult lineResult1 = new LineResult(1, true);
	LineResult lineResult2 = new LineResult(2, false);
	LineResult lineResult3 = new LineResult(3, true);
	
	Column column = new Column(ruleName, ruleType);


	@Test
	public void testColumn() {
		Column column2 = new Column("batatas", Defect.is_feature_envy);
		assertNotEquals("pota",column.getRuleName());
	}

	@Test
	public void testGetRuleType() {
		assertEquals(Defect.is_long, column.getRuleType());
		assertNotEquals("nada", column.getRuleType());
	}

	@Test
	public void testGetArray() {
		List<LineResult> array2 = new ArrayList<LineResult>();
		array2.add(lineResult1);
		array.add(lineResult1);
		assertEquals(array2, array);
		Assert.assertFalse(column.getArray().containsAll(array2));
	}

	@Test
	public void testGetRuleName() {
		assertEquals("regra do francisco", column.getRuleName());
	}

	@Test
	public void testAddResult() {
		column.addResult(lineResult1);
		LineResult lineResult4 = new LineResult(2, false);
		LineResult lineResult5 = new LineResult(1, true);
		assertNotEquals(lineResult4.getMethodID(), column.getArray().get(0).getMethodID());
	}

}
