import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class NetworkTrainer {
	
	/* 
	 * A class that creates, trains, and runs input through a Neural Network object. 
	 */
	
	public static NeuralNetwork network;
	
	/*
	 * trainingDataFilename: the file in which data for training the network is stored. This 
	 * data should have one line at the top to specify how many layers/neurons per layer the 
	 * network should have followed by lines containing input and output data.
	 */
	public NetworkTrainer(String trainingDataFilename) throws IOException {
		network = trainNetwork(trainingDataFilename);
	}
	
	/*
	 * structureDescription: an array of integers specifying how many neurons each layer
	 * in the network to be created will have.
	 */
	static NeuralNetwork createNetwork(String[] structureDescription) {
	    ArrayList<Integer> structure = new ArrayList<Integer>();
	    for(String s : structureDescription) {
	    	structure.add(Integer.parseInt(s));
	    }
	    return new NeuralNetwork(structure);
	}
	
	/*
	 * Parses line from index startIndex for numberOfElements items. line should contain 
	 * doubles. 
	 * 
	 * @requires: startIndex + numberOfElements < line.length 
	 */
	static ArrayList<Double> parseDataString(String[] line, int startIndex, int numberOfElements) {
		
		ArrayList<Double> data = new ArrayList<Double>();
	    
		for(int i = startIndex; i < startIndex + numberOfElements; i++) {
			data.add(Double.parseDouble(line[i]));
		}
	    return data;
	}
	
	/*
	 * Constructs a new NeuralNetwork object and trains it on the data in filename. 
	 * Returns the trained network. 
	 */
	static NeuralNetwork trainNetwork(String filename) {
		try {
			Path filePath = getPathAndCheckFileValid(filename);

			List<String> fileData = Files.readAllLines(filePath, Charset.defaultCharset());
			String[] structureData = fileData.get(0).split(" ");
			int inputSize = Integer.parseInt(structureData[0]);
		    int outputSize = Integer.parseInt(structureData[structureData.length - 1]);
		    
		    NeuralNetwork network = createNetwork(structureData);
		    String[] data;
		    
		    int trainingCycles = Integer.parseInt(fileData.get(1));
		    
		    ArrayList<TrainingSample> trainingData = new ArrayList<TrainingSample>();
		    
		    for(int i = 2; i < fileData.size(); i++) {
		    	data = fileData.get(i).split(" ");
		    	ArrayList<Double> inputData = parseDataString(data, 0, inputSize);
		    	ArrayList<Double> outputData = parseDataString(data, inputSize, outputSize);
		    	trainingData.add(new TrainingSample(inputData, outputData));
		    }
		    
		    for(int i = 0; i < trainingCycles; i++) {
		    	for(TrainingSample sample : trainingData) {
		    		network.trainNetwork(sample.inputs, sample.outputs);
		    	}
		    	
		    	/*
		    	System.out.print("test " + i + ": ");
		    	network.printTotalError();
		    	*/
		    }
		    
		    return network;
		    
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Path getPathAndCheckFileValid(String filename) {
		Path filePath = Paths.get(filename);
		if(!Files.exists(filePath)) {
			System.out.println("That file doesn't exist.");
			System.exit(0);
		}	
		return filePath;
	}	

	/*
	 * args[0] should contain the file containing training data. 
	 */
	public static void main(String[] args) throws IOException {
	
		if(args.length != 1) {
			System.out.println("usage: java NetworkTrainer (trainingData)");
			System.exit(0);
		}	
		NetworkTrainer trainer = new NetworkTrainer(args[0]);
		
	}
	
	
	
}

final class TrainingSample {
	public ArrayList<Double> inputs;
	public ArrayList<Double> outputs;
	
	public TrainingSample(ArrayList<Double> inputs, ArrayList<Double> outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}
}
