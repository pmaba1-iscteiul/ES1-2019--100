package graph;

import utils.DataBase;

public class TestQual {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBase db = new DataBase("C:/Users/alexa/Documents/METI/1st semester/ESI/projeto/Long-Method.xlsx");
		GraphApp ga = new GraphApp(db);
		ga.createHistogramTools();
		ga.createHistogramRules();		
	}

}
