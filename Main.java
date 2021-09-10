

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		if ((args.length < 2) | args[args.length-1].startsWith("-")) {
			System.out.println("Uncorrected parameters...");
			System.exit(1);
		}
		Class<?> clazz = String.class;
		boolean ascending = true;
		
		List<BufferedReader> inputFiles = new ArrayList<BufferedReader>();
		BufferedWriter outputFile = null;
		try{
			outputFile = new BufferedWriter(new FileWriter(args[args.length-1]));
		} catch(IOException e) {
			System.err.println("\n\nThe file cannot be opened for writing...\n\n");
			e.printStackTrace();
			System.exit(1);
		}
		
		for(int i = 0; i < args.length-1; ++i) {
			if (args[i].startsWith("-")) {
				switch(args[i]) {
					case "-s": {
						clazz = String.class;
						break;
					}
					case "-i": {
						clazz = Integer.class;
						break;
					}
					case "-a": {
						ascending = true;
						break;
					}
					case "-d": {
						ascending = false;
						break;
					}
					default: {
						System.err.println("\n\nInvalid key...\n\n");
					}
				}
			} else {
				try {
				BufferedReader br = new BufferedReader(new FileReader(args[i]));
				inputFiles.add(br);
				} catch(FileNotFoundException e) {
					System.err.println("Input file " + args[i] + " not found...");
					
				}
			}
		}
		
		
		InputFiles<?> inpData = null;
		
		if(clazz == String.class)
			inpData = new InputFiles<String>(inputFiles, String.class);
		else
			inpData = new InputFiles<Integer>(inputFiles, Integer.class);
		
		List<?> outData = inpData.returnList();
		if(!ascending)
			Collections.reverse(outData);
		for(Object obj : outData) {
			if(obj instanceof Integer)
				try {
					outputFile.write(obj.toString()+'\n');
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				try {
					outputFile.write(((String)obj)+'\n');
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		
		
		
		try {
			if(outputFile != null)
			outputFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
