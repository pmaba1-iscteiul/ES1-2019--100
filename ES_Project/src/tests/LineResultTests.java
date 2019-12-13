package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rules.LineResult;

public class LineResultTests {
	
	LineResult lineResult1 = new LineResult(1, true);
	LineResult lineResult2 = new LineResult(2, false);
	LineResult lineResult3 = new LineResult(3, true);

	@Test
	public void testLineResult() {
		assertEquals(1, lineResult1.getMethodID());
		assertEquals(false, lineResult2.isResult());
		assertNotEquals(4, lineResult3.getMethodID());
	}

	@Test
	public void testGetMethodID() {
		assertEquals(1, lineResult1.getMethodID());
	}

	@Test
	public void testSetMethodID() {
		LineResult lineResult4 = new LineResult(5,true);
		lineResult4.setMethodID(4);
		assertEquals(4,lineResult4.getMethodID());
	}

	@Test
	public void testIsResult() {
		assertEquals(false, lineResult2.isResult());
	}

	@Test
	public void testSetResult() {
		LineResult lineResult4 = new LineResult(5,true);
		lineResult4.setResult(false);;
		assertEquals(false,lineResult4.isResult());
	}

}
