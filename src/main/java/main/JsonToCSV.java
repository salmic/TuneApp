package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


public class JsonToCSV {

	public static void main(String[] args) throws IOException{
		
		//build up the keySet and dataSet for the given JSON file
		JsonReader jReader = new JsonReader();
		Set<Map<String, String>> dataSet = jReader.buildDataSet(new FileReader(args[0]));
		Set<String> keySet = jReader.getKeys(dataSet);

		//Output the JSON Sets to the given CSV file
		String outputFile = args[1];
		CsvBuilder csvBuilder = new CsvBuilder();
		csvBuilder.createCSV(keySet, dataSet, outputFile);
		
	}
	
}
