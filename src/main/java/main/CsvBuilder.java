package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CsvBuilder {

	//Create the new CSV file from the given data Set
	public void createCSV(ArrayList<String> keySet, ArrayList<Map<String, String>> dataSet, Appendable appendable) throws IOException{
		
		//first build up the header row
		buildCsvHeaders(appendable, keySet);
		
		//then build up the other value rows
		for(Map<String, String> elementMap : dataSet) {
			buildCsvRow(appendable, elementMap, keySet);
		}
		
	}
	
	//Iterate through the given list of keys and add them to the CSV file as column headers
	public void buildCsvHeaders(Appendable appendable, ArrayList<String> keys) throws IOException {
		
		for(int i = 0; i < keys.size() - 1; i++) {
			appendable.append(escaped(keys.get(i)));
			appendable.append(',');
		}
		appendable.append(escaped(keys.get(keys.size() - 1)));
		
		appendable.append("\r\n");
	}

	//Build up each row to be put in a CSV file
	public void buildCsvRow(Appendable appendable, Map<String, String> elementMap, ArrayList<String> keys) throws IOException {
		
		//Iterate through the map to add the values to the row
		for(int i = 0; i < keys.size() - 1; i++) {
			
			//Get the value for a given key, 
			String value = elementMap.get(keys.get(i));
			
			if(value != null) { //check that the value is not null for a given key
				
				//Add the key value pair to the row
				appendable.append(escaped(value));
				
			}
			
			appendable.append(',');
		}
		
		//Add value for last key, without the comma afterwards
		String value = elementMap.get(keys.get(keys.size() - 1));
		if(value != null) {
			appendable.append(escaped(value));
		}
		
		appendable.append("\r\n");

	}
	
	//Check if the given String has any characters that require escaping for CSV and add the necessary escape characters
	public String escaped(String tempString) {
		
		if(tempString != null) {
			//check if the value contains double quotes and add an extra double quote next to the existing double quote
			tempString = tempString.replace("\"", "\"\"");
			
			//check if the string has comma, line break, or single quotes and surround with double quotes if need
			if(tempString.contains(",") || tempString.contains("\n") || tempString.contains("\r") || tempString.contains("\"")) {
				tempString = '"' + tempString + '"';
			} 
		}
		
		return tempString;
	}

}
