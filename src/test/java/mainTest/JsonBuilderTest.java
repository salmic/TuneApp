package mainTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import main.JsonReader;

public class JsonBuilderTest {

	@Test
    public void testGetJSONArrayReturnsJSONArray() throws FileNotFoundException {
        //Setup JsonBuilder and FileReader needed for the getJSONArray method
		JsonReader jBuilder = new JsonReader();
        FileReader reader = new FileReader("test.json");
        
        //Get JsonArray from the method being tested
        JsonArray testArray = jBuilder.getJSONArray(reader);
   
        //Setup objects to be added to a JsonArray
        JsonObject jObject1 = new JsonObject();
        jObject1.addProperty("id", 1);
        jObject1.addProperty("name", "Suzy");
        jObject1.addProperty("type", "Person");
        
        JsonObject jObject2 = new JsonObject();
        jObject2.addProperty("id", 2);
        jObject2.addProperty("name", "Cleo");
        jObject2.addProperty("type", "Cat");
        
        JsonObject jObject3 = new JsonObject();
        jObject3.addProperty("id", 3);
        jObject3.addProperty("name", "Asha");
        jObject3.addProperty("type", "Dog");
        
        //Setup a JsonArray that will be used to compare against the one from the method response
        JsonArray actualArray = new JsonArray();
        actualArray.add(jObject1);
        actualArray.add(jObject2);
        actualArray.add(jObject3);
        
        assertEquals(testArray, actualArray);
    }
	
	@Test
    public void testBuildDataSet() throws FileNotFoundException {
        //Setup JsonBuilder and FileReader needed for the getJSONArray method
		JsonReader jBuilder = new JsonReader();
        FileReader reader = new FileReader("test.json");
        
        //Get Set of JsonObject Maps from method being tested
        Set<Map<String, String>> testSet = jBuilder.buildDataSet(reader);
        
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
        
        Set<Map<String, String>> actualSet = new LinkedHashSet<Map<String,String>>();
        actualSet.add(map1);
        actualSet.add(map2);
        actualSet.add(map3);
        
        assertEquals(testSet, actualSet);
    }
	
	@Test
    public void testBuildJsonObjectMap() {
		JsonReader jBuilder = new JsonReader();
		
		//Setup JsonObject to hand to method being tested
		JsonObject jObject = new JsonObject();
		jObject.addProperty("id", 1);
		jObject.addProperty("name", "Suzy");
		jObject.addProperty("type", "Person");
		
		//store response from method under test
        Map<String, String> testMap = jBuilder.buildJsonObjectMap(jObject);
        
        //Create a map to use in comparison with response
        Map<String,String> actualMap = new LinkedHashMap<String,String>();
        actualMap.put("id", "1");
        actualMap.put("name", "Suzy");
        actualMap.put("type", "Person");
        
        assertEquals(testMap, actualMap);
    }
	
	@Test
    public void testGetKeys() {
		JsonReader jBuilder = new JsonReader();
		
		//Setup Set of Maps to be passed to the method under test
		Map<String,String> map1 = new LinkedHashMap<String,String>();
        map1.put("id", "1");
        map1.put("name", "Suzy");
        map1.put("type", "Person");
        
        Map<String,String> map2 = new LinkedHashMap<String,String>();
        map2.put("id", "2");
        map2.put("name", "Cleo");
        map2.put("year", "1995");
        
        Map<String,String> map3 = new LinkedHashMap<String,String>();
        map3.put("id", "3");
        map3.put("name", "Asha");
        map3.put("city", "Seattle");
        
        Set<Map<String, String>> dataSet = new LinkedHashSet<Map<String,String>>();
        dataSet.add(map1);
        dataSet.add(map2);
        dataSet.add(map3);
        
        //Store set of keys that is returned by the getKeys method
        Set<String> testSet = jBuilder.getKeys(dataSet);
        
        //Create a set of keys to use in comparison
        Set<String> actualSet = new LinkedHashSet<String>();
        actualSet.add("id");
        actualSet.add("name");
        actualSet.add("type");
        actualSet.add("year");
        actualSet.add("city");
        
        //assert that the test set of keys and the actual set of keys are the same
        assertEquals(testSet, actualSet);
    }

}
