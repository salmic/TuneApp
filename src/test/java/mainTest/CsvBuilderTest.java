package mainTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;
import main.CsvBuilder;

public class CsvBuilderTest extends TestCase {
	
	@Test
	public void createCsvTest() throws IOException {
		CsvBuilder builder = new CsvBuilder();
		
		 //Setup a Set of keys
		ArrayList<String> keySet = new ArrayList<String>();
		keySet.add("id");
		keySet.add("name");
		keySet.add("type");
		keySet.add("date");
		
		//Setup Set of Maps to be used to check response from method being tested
        Map<String,String> map1 = new LinkedHashMap<String,String>();
        map1.put("id", "1");
        map1.put("name", "Suzy");
        map1.put("type", "Person");
        
        Map<String,String> map2 = new LinkedHashMap<String,String>();
        map2.put("id", "2");
        map2.put("name", "Cleo");
        map2.put("type", "Cat");
        
        Map<String,String> map3 = new LinkedHashMap<String,String>();
        map3.put("id", "3");
        map3.put("name", "Asha");
        map3.put("type", "Dog");
        map3.put("date", "1995");
        
        ArrayList<Map<String, String>> dataSet = new ArrayList<Map<String,String>>();
        dataSet.add(map1);
        dataSet.add(map2);
        dataSet.add(map3);
		
		//Setup a StringBuilder to be used in the test and pass it to the buildCsvRow method
		StringBuilder testSB = new StringBuilder();
		builder.createCSV(keySet, dataSet, testSB);
		
		// assert the method under test returns the expected value
		assertTrue(testSB.toString().equals("id,name,type,date\r\n1,Suzy,Person,\r\n2,Cleo,Cat,\r\n3,Asha,Dog,1995\r\n"));
	}
	
	@Test
    public void testBuildCsvHeadersReturnsProperlyAnnotatedString() throws IOException {
        CsvBuilder builder = new CsvBuilder();
        
        //Setup a Set of keys
        ArrayList<String> keySet = new ArrayList<String>();
        keySet.add("test1");
        keySet.add("test2");
        keySet.add("test3");
        
        //Setup a StringBuilder to be used in the test and pass it to the buildCsvHeaders method
        StringBuilder testSB = new StringBuilder();
        builder.buildCsvHeaders(testSB, keySet);

        // assert the two strings are equal
        assertTrue(testSB.toString().equals("test1,test2,test3\r\n"));
    }
	
	@Test
    public void testBuildCsvRowReturnsProperlyAnnotatedString() throws IOException {
        CsvBuilder builder = new CsvBuilder();
        
        //Setup a Set of keys
        ArrayList<String> keySet = new ArrayList<String>();
        keySet.add("1");
        keySet.add("2");
        keySet.add("3");
        keySet.add("4");
        
        //Setup a Map of the Key Value pairs to be used in the row
        Map<String, String> dataMap = new LinkedHashMap<String, String>();
        dataMap.put("1", "test1");
        dataMap.put("2", "test2");
        dataMap.put("4", "test4");
        
        //Setup a StringBuilder to be used in the test and pass it to the buildCsvRow method
        StringBuilder testSB = new StringBuilder();
        builder.buildCsvRow(testSB, dataMap, keySet);

        // assert the method under test returns the expected value
        assertTrue(testSB.toString().equals("test1,test2,,test4\r\n"));
    }
	
	@Test
    public void testEscapedReturnsProperlyCSVEscapedString() {
        CsvBuilder builder = new CsvBuilder();
        
        //Setup strings that need to be escaped
        String testString1 = builder.escaped("He\"llo World");
        String testString2 = builder.escaped("Hello W\norld");
        String testString3 = builder.escaped("Hello W\rorld");
        String testString4 = builder.escaped("He,llo World");
        String testString5 = builder.escaped("He,llo Wo\"rld");
        	
        //Setup strings that will be used to check against the response from the method being tested
        String actualString1 = "\"He\"\"llo World\"";
        String actualString2 = "\"Hello W\norld\"";
        String actualString3 = "\"Hello W\rorld\"";
        String actualString4 = "\"He,llo World\"";
        String actualString5 = "\"He,llo Wo\"\"rld\"";

        // assert the two strings are equal
        assertTrue(testString1.equals(actualString1));
        assertTrue(testString2.equals(actualString2));
        assertTrue(testString3.equals(actualString3));
        assertTrue(testString4.equals(actualString4));
        assertTrue(testString5.equals(actualString5));
    }

}
