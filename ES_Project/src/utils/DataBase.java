package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import excelReader.ExcelReader;
import excelReader.FileRow;
import rules.BuildRules;
import rules.Column;
import rules.Rule;

public class DataBase {

	private List<FileRow>excel_file;
	private List<Column> columns;
	private List<Rule> rules;

	public DataBase(String path) {
		super();
		this.excel_file = ExcelReader.ReadFile(path);
		this.columns = new ArrayList<Column>();
		this.rules = new ArrayList<Rule>();
	}

	public List<FileRow> getExcel_file() {
		return excel_file;
	}
	public List<Column> getColumns(){
		return columns;
	}

	public void addColumn(Column column) {
		Iterator<Column> it = columns.iterator();
		
		while(it.hasNext()) {
			Column col = it.next();
			if(col.getRuleName().equals(column.getRuleName()))
					it.remove();
		}
		columns.add(column);
	}
	
	public void addRule(Rule r) {
		Iterator<Rule> it =  rules.iterator();
		
		while(it.hasNext()) {
			Rule rule = it.next();
			if(rule.getRuleName().equals(r.getRuleName()))
				it.remove();
		}
		rules.add(r);
		new BuildRules(r, this).calculate();;
		System.out.println(r.toString());
	}
	
	public List<Rule> getRules() {
		return rules;
	}

	public List<String> getRulesName(){
		List<String> names = new ArrayList<String>();

		for(Column c: columns)
			names.add(c.getRuleName());

		return names;
	}
	
	public List<String> getResults(){
		List<String> results = new ArrayList<String>();
		
		for(int i = 0; i < getExcel_file().size(); i++) {
			String s = getExcel_file().get(i).toString();
			for(int j = 0; j < columns.size(); j++) {
				s += columns.get(j).getArray().get(i).toString(); 
			}
			results.add(s);
		}
		
		return results;
	}

}
