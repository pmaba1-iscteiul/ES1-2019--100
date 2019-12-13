package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import excelReader.ExcelReader;

public class ExcelReadertest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testReadFile() {
		assertEquals(420, ExcelReader.ReadFile("D:/Computer_Files/Downloads/Long-Method.xlsx").size());
	}
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void testExeceptio() {
		ExcelReader.ReadFile("asdf");
		expected.expect(FileNotFoundException.class);
	}

}
