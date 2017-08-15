package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


public class JsonToCSV {

	public static void main(String[] args) throws IOException{
		
		if(args.length < 2) {
			System.out.println("Please provide the JSON filename and output file as parameters");
			System.exit(0);
		}
		
		//build up the keySet and dataSet for the given JSON file
		JsonReader jReader = new JsonReader();
		Set<Map<String, String>> dataSet = jReader.buildDataSet(new FileReader(args[0]));
		Set<String> keySet = jReader.getKeys(dataSet);

		//Output the JSON file data to the given output file name
		String outputFile = args[1];
		
		CsvBuilder csvBuilder = new CsvBuilder();
		FileWriter writer = new FileWriter(new File(outputFile));
		csvBuilder.createCSV(keySet, dataSet, writer);
	
		writer.close();
		
	}
	
}
