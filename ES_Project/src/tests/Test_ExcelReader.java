package tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import excelReader.ExcelReader;
import excelReader.FileRow;

class Test_ExcelReader {

	@Test
	void testExcelReader() {
		ExcelReader e = new ExcelReader();
	}

	@Test
	void testReadFile() {
		List <FileRow> test = ExcelReader.ReadFile("C:/Users/pedro/Desktop/Long-Method.xlsx");
		assertEquals(420, test.size());

	}

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	void testReadFileWithWrongFilePath() {
		ExcelReader.ReadFile("aaa");
		expected.expect(FileNotFoundException.class);
	}

}
