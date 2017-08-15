package main;

import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonReader {

	//Create a JSON array from input file
	public JsonArray getJSONArray(FileReader reader) {
		JsonElement root = new JsonParser().parse(reader);
		return root.getAsJsonArray();
	}
	
	//Get the list of CSV column headers from the JSON Array
	public Set<String> getKeys(Set<Map<String, String>> dataSet){
		Set<String> keys = new LinkedHashSet<String>();
		
		//iterate through the dataSet and compile a Set of all of the unique keys
		for(Map<String, String> objectMap : dataSet) {
			for(Entry<String, String> entry : objectMap.entrySet()) {
				keys.add(entry.getKey());
			}
		}
		
		return keys;
		
	}
	
	//build a set of JSON object maps
	public Set<Map<String, String>> buildDataSet(FileReader reader) {
		JsonArray jsonArray = getJSONArray(reader);
		
		Set<Map<String, String>> dataSet = new LinkedHashSet<Map<String,String>>();
		
		//iterate through each JSON object in the array to build the set of JSON object maps
		for(int i = 0; i < jsonArray.size(); i++) {
			JsonObject tempObject = jsonArray.get(i).getAsJsonObject(); //Get the JSON object at index i
			dataSet.add(buildJsonObjectMap(tempObject)); //Get the map of the object's key value pairs
			
		}

		return dataSet;
	}
	
	// build a map of a JSON object's key value pairs as Strings
	public Map<String, String> buildJsonObjectMap(JsonObject tempObject) {
		Map<String, String> jsonMap = new LinkedHashMap<String, String>();
		
		for(Entry<String, JsonElement> entry : tempObject.entrySet()) {
			String tempString = entry.getValue().getAsString(); 
			
			//Add the key value pair to the map
			jsonMap.put(entry.getKey(), tempString);
		}
			
		return jsonMap;
	}
}
