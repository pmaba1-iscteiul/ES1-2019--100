package excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	/**
	 * Read File was the method used to read the excel file. It reads all the rows and put the row's data into a List.
	 * In order to read this file the API from APACHE POI was used
	 * @param String "FilePath"
	 * @return List<FileRow>
	 */
	public static List<FileRow> ReadFile (String FilePath) {
		
		List<FileRow> rows = new ArrayList<>();
		try {

			FileInputStream f = new FileInputStream (new File (FilePath));

			XSSFWorkbook w = new XSSFWorkbook(f);
			XSSFSheet first = w.getSheetAt(0);
			Iterator <Row> rowit = first.iterator();
			Row nextRow = rowit.next();

			while (rowit.hasNext()) {
				nextRow = rowit.next();
				Iterator <Cell> cellit = nextRow.cellIterator();
				int MethodID  = 0;
				String Package = "";
				String ClassName = "";
				String Method = "";
				int LOC = 0;
				int CYCLO = 0;
				int ATFD = 0;
				double LAA = 0.0;
				boolean is_Long_Method = false;
				boolean PMD = false;
				boolean iPlasma = false;
				boolean is_Feature_Envy = false;

				while (cellit.hasNext()) {
					Cell nextCell = cellit.next();
					int index = nextCell.getColumnIndex();

					switch (index) {

					case 0:
						MethodID = (int) nextCell.getNumericCellValue();
						break;

					case 1:
						Package = nextCell.getStringCellValue();
						break;

					case 2:
						ClassName = nextCell.getStringCellValue();
						break;

					case 3:
						Method = nextCell.getStringCellValue();
						break;

					case 4:
						LOC = ((int) nextCell.getNumericCellValue()); 
						break;

					case 5:
						CYCLO = ((int) nextCell.getNumericCellValue());
						break;

					case 6:
						ATFD = ((int) nextCell.getNumericCellValue());
						break;

					case 7:
						CellType type = nextCell.getCellType();
						if (type == CellType.NUMERIC){
							LAA = (nextCell.getNumericCellValue());
						}
						else{
							LAA = (Double.parseDouble(nextCell.getStringCellValue()));
						}
						break;

					case 8:
						is_Long_Method = (nextCell.getBooleanCellValue());
						break;

					case 9:
						iPlasma = (nextCell.getBooleanCellValue());
						break;

					case 10:
						PMD = (nextCell.getBooleanCellValue());
						break;

					case 11:
						is_Feature_Envy = (nextCell.getBooleanCellValue());
						break;
					}
				}
				rows.add(new FileRow (MethodID, Package, ClassName, Method, LOC, CYCLO, ATFD, LAA, is_Long_Method, iPlasma, PMD, is_Feature_Envy));

			}

			w.close();
			f.close();

		}
		catch (IOException e){
			e.printStackTrace();
		}
		return rows;
	}

}