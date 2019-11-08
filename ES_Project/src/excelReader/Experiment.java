package excelReader;

public class Test {

	public static void main(String[] args) {
		
		ExcelReader e = new ExcelReader();
		e.ReadFile("C:/Users/pedro/Desktop/Long-Method.xlsx");
		System.out.println(e.getRows().get(133).getRow());

	}

}
