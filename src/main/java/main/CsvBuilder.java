package main;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CsvBuilder {

	//Create the new CSV file from the given data Set
	public Appendable createCSV(Set<String> keySet, Set<Map<String, String>> dataSet, Appendable appendable) throws IOException{
		
		//first build up the header row
		buildCsvHeaders(appendable, keySet);
		
		//then build up the other value rows
		for(Map<String, String> elementMap : dataSet) {
			buildCsvRow(appendable, elementMap, keySet);
		}
		
		return appendable;
		
	}
	
	//Iterate through the given list of keys and add them to the CSV file as column headers
	public void buildCsvHeaders(Appendable appendable, Set<String> keySet) throws IOException {
		
		for(String s : keySet) {
			appendable.append(escaped(s));
			appendable.append(',');
		}
		
		appendable.append("\r\n");
	}

	//Build up each row to be put in a CSV file
	public void buildCsvRow(Appendable appendable, Map<String, String> elementMap, Set<String> keys) throws IOException {
		
		//Iterate through the map to add the values to the row
		for(String key : keys) {
			
			//Get the value for a given key, 
			String value = elementMap.get(key);
			
			if(value != null) { //check that the value is not null for a given key
				
				//Add the key value pair to the row
				appendable.append(escaped(value));
				
			} else {
				
				//If the value is null for a given key, add a comma to the row
			}
			appendable.append(',');
		}
		
		appendable.append("\r\n");

	}
	
	//Check if the given String has any characters that require escaping for CSV and add the necessary escape characters
	public String escaped(String tempString) {
		
		//check if the value contains double quotes and add an extra double quote next to the existing double quote
		tempString = tempString.replace("\"", "\"\"");
		
		//check if the string has comma, line break, or single quotes and surround with double quotes if need
		if(tempString.contains(",") || tempString.contains("\n") || tempString.contains("\r") || tempString.contains("\"")) {
			tempString = '"' + tempString + '"';
		} 
		
		return tempString;
	}

}
