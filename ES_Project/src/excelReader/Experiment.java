package excelReader;

import java.util.List;

public class Experiment {

	public static void main(String[] args) {
		
		List<FileRow> rows = ExcelReader.ReadFile("C:/Users/pedro/Desktop/Long-Method.xlsx");
		System.out.println(rows.get(133).getRow());

	}
}
