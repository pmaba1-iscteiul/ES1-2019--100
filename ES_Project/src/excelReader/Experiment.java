package excelReader;

import java.util.List;

/**
 * 
 * The aim of this class is to test the reading of the excel file
 * 
 * @author Pedro Batoca
 *
 */
public class Experiment {

	public static void main(String[] args) {
		
		List<FileRow> rows = ExcelReader.ReadFile("C:/Users/pedro/Desktop/Long-Method.xlsx");
		System.out.println(rows.get(133).getRow());

	}
}
