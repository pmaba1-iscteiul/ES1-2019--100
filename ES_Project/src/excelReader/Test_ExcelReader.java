package excelReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

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
