package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BuildRulesTests.class, ColumnTests.class, DataBase_Test.class, ExcelReadertest.class, FileRowTest.class,
		GraphApp_Test.class, LineResultTests.class, RulePartTests.class, RuleTests.class })
public class AllTests {

}
