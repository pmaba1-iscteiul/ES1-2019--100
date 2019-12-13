package tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import excelReader.FileRow;

public class FileRowTest {
	
	static final FileRow aux = new FileRow(211,"org.lnicholls.galleon.apps.desktop","Desktop.DesktopScreen","update()",137.0,7.0,0.0,0.0, false, false, false, false);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetMethodID() {
		assertEquals(211, aux.getMethodID());
	}

	@Test
	public void testGetPackage() {
		assertEquals("org.lnicholls.galleon.apps.desktop", aux.getPackage());
	}

	@Test
	public void testGetClassName() {
		assertEquals("Desktop.DesktopScreen", aux.getClassName());
	}

	@Test
	public void testGetMethod() {
		assertEquals("update()", aux.getMethod());
	}

	@Test
	public void testGetLOC() {
		assertEquals(137.0, aux.getLOC(), 0);
	}

	@Test
	public void testGetCYCLO() {
		assertEquals(7.0, aux.getCYCLO(), 0);
	}

	@Test
	public void testGetATFD() {
		assertEquals(0.0, aux.getATFD(), 0);
	}

	@Test
	public void testGetLAA() {
		assertEquals(0.0, aux.getLAA(), 0);
	}

	@Test
	public void testIsIs_Long_Method() {
		assertEquals(false, aux.isIs_Long_Method());
	}

	@Test
	public void testIsPMD() {
		assertEquals(false, aux.isPMD());
	}

	@Test
	public void testIsiPlasma() {
		assertEquals(false, aux.isiPlasma());
	}

	@Test
	public void testIsIs_Feature_Envy() {
		assertEquals(false, aux.isIs_Feature_Envy());
	}

	@Test
	public void testToString() {
		assertEquals("211 org.lnicholls.galleon.apps.desktop Desktop.DesktopScreen update() 137.0 7.0 0.0 0.0 false false false false", aux.toString());
	}

}
