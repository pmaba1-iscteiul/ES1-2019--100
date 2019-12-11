package utils;

import java.util.ArrayList;
import java.util.List;

import rules.Column;
import excelReader.ExcelReader;
import excelReader.FileRow;
import quality.ToolsQuality;

public class DataBase {

	private List<FileRow>excel_file;
	private ToolsQuality tools;
	private List<Column> columns;

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


	public List<Column> getColumns(){
		return columns;
	}

	public void addColumn (Column column) {
		columns.add(column);
	}

	public List<String> getRulesName(){
		List<String> names = new ArrayList<String>();

		for(Column c: columns)
			names.add(c.getRuleName());

		return names;
	}

}
