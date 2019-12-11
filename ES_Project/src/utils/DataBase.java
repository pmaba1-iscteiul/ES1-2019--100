package utils;

import java.util.List;

import excelReader.ExcelReader;
import excelReader.FileRow;
import rules.Column;

public class DataBase {
	
	private List<FileRow>excel_file;
	private List<Column> columns;

	public DataBase(String path) {
		super();
		this.excel_file = ExcelReader.ReadFile(path);
	}

	public List<FileRow> getExcel_file() {
		return excel_file;
	}
	
	public List<Column> getColumns(){
		return columns;
	}
	
	public void addColumn (Column column) {
		columns.add(column);
	}
}
