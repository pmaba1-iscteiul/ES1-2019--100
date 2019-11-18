package quality;

import utils.DataBase;

public class TestTools {

	public static void main(String[] args) {
		DataBase db = new DataBase("C:/Users/alexa/Documents/METI/ESI/projeto/Long-Method.xlsx");
		//System.out.println(db.getExcel_file());
		ToolsQuality tq = new ToolsQuality(db);
		System.out.println("PDM:");
		System.out.println("DCI =" + tq.getPMD_DCI());
		System.out.println("DII =" + tq.getPMD_DII());
		System.out.println("ADCI =" + tq.getPMD_ADCI());
		System.out.println("ADII =" + tq.getPMD_ADII());
		System.out.println("iPlasma:");
		System.out.println("DCI =" + tq.getiPlasma_DCI());
		System.out.println("DII =" + tq.getiPlasma_DII());
		System.out.println("ADCI =" + tq.getiPlasma_ADCI());
		System.out.println("ADII =" + tq.getiPlasma_ADII());
		
	}
}
