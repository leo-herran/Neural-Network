import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class characterRecognizer extends NetworkTrainer {

	/* 
	 * A map from three boolean values to single character.
	 */	
	private HashMap<ArrayList<Integer>, String> letterMap;

	/* 
	 * If the neural network outputs a value higher/lower than this then it is assumed
	 * to be a 1/-1. Otherwise, the output cannot be determined. 	
	 */
	private static final double outputThreshold = 0.5; 

	public characterRecognizer(String trainingDataFilename, String letterFilename) throws IOException {
		super(trainingDataFilename);
		createLetterMap(letterFilename);
	}
	
	public void createLetterMap(String letterFilename) {
		Path letterPath = super.getPathAndCheckFileValid(letterFilename);	
		letterMap = new HashMap<ArrayList<Integer>, String>();
		
		try {
			List<String> letterData = Files.readAllLines(letterPath, Charset.defaultCharset());
			
			int sizeOfEncoding = Integer.parseInt(letterData.get(0));
		    
			String[] lineData;
			ArrayList<Integer> encoding;
			for(int i = 1; i < letterData.size(); i++) {
				lineData = letterData.get(i).split(" ");
				encoding = new ArrayList<Integer>(sizeOfEncoding);
				
				for(int j = 0; j < sizeOfEncoding; j++) {
					encoding.add(Integer.parseInt(lineData[j]));
				}
				letterMap.put(encoding, lineData[sizeOfEncoding]);
				
			}
		    
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void runInput(String letterInputFilename) {
		
		try {
			Path inputPath = super.getPathAndCheckFileValid(letterInputFilename);	
			ArrayList<Integer> result = new ArrayList<Integer>();
			List<String> inputData = Files.readAllLines(inputPath, Charset.defaultCharset());
			
			String[] inputLine;
			ArrayList<Double> inputValues = new ArrayList<Double>();
			ArrayList<Double> outputValues = new ArrayList<Double>();
			
			for(int i = 0; i < inputData.size(); i++) {
				inputLine = inputData.get(i).split(" ");
				for(String s : inputLine) {
					inputValues.add(Double.parseDouble(s));
				}
				network.enterData(inputValues);
				outputValues = network.getOutputValues();
				System.out.println(getCharacter(outputValues));
				
				inputValues.clear();
			}
		    
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getCharacter(ArrayList<Double> outputEncoding) {
		ArrayList<Integer> out = new ArrayList<Integer>(outputEncoding.size());
		for(int i = 0; i < outputEncoding.size(); i++) {
			Double d = outputEncoding.get(i);
			if(d > outputThreshold) {
				out.add(1);
			} else if (d < -1*outputThreshold) {
				out.add(0);
			} else {
				out.add(-1); //inconclusive/ambiguous output
			}
		}
		
		if(letterMap.containsKey(out)) {
			return letterMap.get(out);
		} else {
			//Either the letter encoding is not in the map or an 
			//output value was inconclusive. 
			return "0"; 
		}
	}

	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			System.out.println("usage: java characterRecognizer (trainingData) (letterMappingData) (inputData)");
			System.exit(0);
		}
		characterRecognizer rec = new characterRecognizer(args[0], args[1]);
		rec.runInput(args[2]);
	}
}
