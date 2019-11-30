package utils;

import java.util.List;


import excelReader.ExcelReader;
import excelReader.FileRow;
import quality.ToolsQuality;

public class DataBase {
	
	private List<FileRow>excel_file;
	private ToolsQuality tools;

	public DataBase(String path) {
		super();
		this.excel_file = ExcelReader.ReadFile(path);
		this.tools = new ToolsQuality(this);

	}

	public List<FileRow> getExcel_file() {
		return excel_file;
	}
	
	public ToolsQuality getTools() {
		return tools;
	} 
	
}
