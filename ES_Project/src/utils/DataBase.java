package utils;

import java.util.List;

import excelReader.ExcelReader;
import excelReader.FileRow;

public class DataBase {
	
	private List<FileRow>excel_file;

	public DataBase(String path) {
		super();
		this.excel_file = ExcelReader.ReadFile(path);
	}

	public List<FileRow> getExcel_file() {
		return excel_file;
	}
}
