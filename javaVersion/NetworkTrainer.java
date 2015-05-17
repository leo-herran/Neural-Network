import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class NetworkTrainer {
	
	/* 
	 * A class that creates, trains, and runs input through a Neural Network object. 
	 */
	
	public NeuralNetwork network;
	
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
	static NeuralNetwork trainNetwork(String filename) throws IOException {
		InputStream fis = new FileInputStream(filename);
		InputStreamReader isr = new InputStreamReader(fis);
	    BufferedReader br = new BufferedReader(isr);
	    
	    String[] structureData = br.readLine().split(" ");
	    int inputSize = Integer.parseInt(structureData[0]);
	    int outputSize = Integer.parseInt(structureData[structureData.length - 1]);
	    
	    NeuralNetwork network = createNetwork(structureData);
	    
	    String line;
	    String[] data;
	    while ((line = br.readLine()) != null) {
	    	data = line.split(" ");
	    	ArrayList<Double> inputData = parseDataString(data, 0, inputSize);
	    	ArrayList<Double> outputData = parseDataString(data, inputSize, outputSize);
	    	network.trainNetwork(inputData, outputData);
	    	
	    	ArrayList<Layer> netData = network.net;
	    	Layer outputLayer = netData.get(netData.size() - 1);
	    	
	    	System.out.println("test---------");
	    	
	    	for(int i = 0; i < outputLayer.size(); i++) {
	    		String actual = String.format("%.4f", outputLayer.getNeuron(i).outputValue);
	    		System.out.println(i + "  " + actual + " : " + outputData.get(i));
	    	}
	    }
	    
	    return network;	
	}
	
	/*
	 * args[0] should contain the file containing training data. 
	 */
	public static void main(String[] args) throws IOException {
		
		NetworkTrainer trainer = new NetworkTrainer(args[0]);
		
	}
}
