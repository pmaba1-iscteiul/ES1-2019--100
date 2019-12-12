package excelReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class Test_Excel_Reader {
	
	List <FileRow> test = ExcelReader.ReadFile("C:/Users/pedro/Desktop/Long-Method.xlsx");
	FileRow aux = test.get(210);

	@Test
	void checkExcelReader() {
		ExcelReader e = new ExcelReader();
	}
	
	
	@Test
	void numberOfRows() {
		assertEquals(420, test.size());
	}
	
	@Test
	void checkFileRowContent() {
		assertEquals(211, aux.getMethodID());
		assertEquals("org.lnicholls.galleon.apps.desktop", aux.getPackage());
		assertEquals("Desktop.DesktopScreen", aux.getClassName());
		assertEquals("update()", aux.getMethod());
		assertEquals(137.0, aux.getLOC());
		assertEquals(7.0, aux.getCYCLO());
		assertEquals(0.0, aux.getATFD());
		assertEquals(0.0, aux.getLAA());
		assertEquals(false, aux.isIs_Long_Method());
		assertEquals(false, aux.isPMD());
		assertEquals(false, aux.isiPlasma());
		assertEquals(false, aux.isIs_Feature_Envy());
	}
	
	@Test
	void checkGetRow() {
		assertEquals("211;org.lnicholls.galleon.apps.desktop;Desktop.DesktopScreen;update();137.0;7.0;0.0;0.0;false;false;false;false",
				aux.toString());
	}
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	void checkBadFileCreation() {
				ExcelReader.ReadFile("aaa");
				expected.expect(FileNotFoundException.class);
	}

}
