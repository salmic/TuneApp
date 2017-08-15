package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CsvBuilder {

	//Create the new CSV file from the given data Set
	public void createCSV(Set<String> keySet, Set<Map<String, String>> dataSet, String fileName) throws IOException{

		StringBuilder sb = new StringBuilder();
		
		//first build up the header row
		sb = buildCsvHeaders(sb, keySet);
		
		//then build up the other value rows
		for(Map<String, String> elementMap : dataSet) {
			sb = buildCsvRow(sb, elementMap, keySet);
		}
		
		writeToFile(sb, fileName);
		
	}
	
	//Iterate through the given list of keys and add them to the CSV file as column headers
	public StringBuilder buildCsvHeaders(StringBuilder sb, Set<String> keySet) {
		
		for(String s : keySet) {
			sb.append(escaped(s));
			sb.append(',');
		}
		
		sb.append("\r\n");

		return sb;
	}

	//Build up each row to be put in a CSV file
	public StringBuilder buildCsvRow(StringBuilder builder, Map<String, String> elementMap, Set<String> keys) {
		
		//Iterate through the map to add the values to the row
		for(String key : keys) {
			
			//Get the value for a given key, 
			String value = elementMap.get(key);
			
			if(value != null) { //check that the value is not null for a given key
				
				//Add the key value pair to the row
				builder.append(escaped(value));
				builder.append(',');
				
			} else {
				
				//If the value is null for a given key, add a comma to the row
				builder.append(',');
			}
		}
		
		builder.append("\r\n");
		
		return builder;
	}
	
	//Write a given stringBuilder out to a file
	public static void writeToFile(StringBuilder stringBuilder, String fileName) throws IOException {
		FileWriter writer = new FileWriter(new File(fileName));
		
		//write the CSV data to the file
		writer.write(stringBuilder.toString());
		writer.close();
		
	}
	
	//Check if the given String has any characters that require escaping for CSV and add the necessary escape characters
	public String escaped(String tempString) {
		
		//check if the string has comma, line break, or single quotes and surround with double quotes if need
		if(tempString.contains(",") || tempString.contains("\n") || tempString.contains("\r")) {
			tempString = '"' + tempString + '"';
		
		//check if the value contains double quotes and add an extra double quote next to the existing double quote
		} else if (tempString.contains("\"")) {
			int charIndex = tempString.indexOf("\"");
			
			//add a double quote to any existing double quote to escape it
			tempString = '"' + tempString.substring(0, charIndex) + '"' + tempString.substring(charIndex) + '"';
		}
		
		return tempString;
	}

}
