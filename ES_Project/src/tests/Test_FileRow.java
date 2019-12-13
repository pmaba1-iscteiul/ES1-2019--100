package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import excelReader.FileRow;

class Test_FileRow {	
	
	FileRow aux = new FileRow(211,"org.lnicholls.galleon.apps.desktop","Desktop.DesktopScreen","update()",137.0,7.0,0.0,0.0, false, false, false, false);;

	@Test
	void testFileRow() {
		FileRow aux2 = new FileRow(211,"org.lnicholls.galleon.apps.desktop","Desktop.DesktopScreen","update()",137.0,7.0,0.0,0.0, false, false, false, false);
	}

	@Test
	void testGetMethodID() {
		assertEquals(211, aux.getMethodID());
	}

	@Test
	void testGetPackage() {
		assertEquals("org.lnicholls.galleon.apps.desktop", aux.getPackage());
	}

	@Test
	void testGetClassName() {
		assertEquals("Desktop.DesktopScreen", aux.getClassName());
	}

	@Test
	void testGetMethod() {
		assertEquals("update()", aux.getMethod());
	}

	@Test
	void testGetLOC() {
		assertEquals(137.0, aux.getLOC());
	}

	@Test
	void testGetCYCLO() {
		assertEquals(7.0, aux.getCYCLO());
	}

	@Test
	void testGetATFD() {
		assertEquals(0.0, aux.getATFD());
	}

	@Test
	void testGetLAA() {
		assertEquals(0.0, aux.getLAA());
	}

	@Test
	void testIsIs_Long_Method() {
		assertEquals(false, aux.isIs_Long_Method());
	}

	@Test
	void testIsPMD() {
		assertEquals(false, aux.isPMD());
	}

	@Test
	void testIsiPlasma() {
		assertEquals(false, aux.isiPlasma());
	}

	@Test
	void testIsIs_Feature_Envy() {
		assertEquals(false, aux.isIs_Feature_Envy());
	}

	@Test
	void testToString() {
		assertEquals("211;org.lnicholls.galleon.apps.desktop;Desktop.DesktopScreen;update();137.0;7.0;0.0;0.0;false;false;false;false",
				aux.toString());
	}

}
