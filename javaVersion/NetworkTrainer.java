import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class NetworkTrainer {
	
	public NeuralNetwork network;
	
	
	public NetworkTrainer(String trainingDataFilename) throws IOException {
		network = createAndTrainNetwork(trainingDataFilename);
		
	}
	
	
	static NeuralNetwork createNN(String[] structureDescription) {
		
	    ArrayList<Integer> structure = new ArrayList<Integer>();
	    for(String s : structureDescription) {
	    	structure.add(Integer.parseInt(s));
	    }
	    
	    return new NeuralNetwork(structure);
	}
	
	
	static ArrayList<Double> parseDataString(String[] line, int startIndex, int numberOfElements) {
		
		ArrayList<Double> data = new ArrayList<Double>();
	    
		for(int i = startIndex; i < startIndex + numberOfElements; i++) {
			data.add(Double.parseDouble(line[i]));
		}
	    return data;
	}
	
	
	static NeuralNetwork createAndTrainNetwork(String filename) throws IOException {
		InputStream fis = new FileInputStream(filename);
		InputStreamReader isr = new InputStreamReader(fis);
	    BufferedReader br = new BufferedReader(isr);
	    
	    String[] structureData = br.readLine().split(" ");
	    int inputSize = Integer.parseInt(structureData[0]);
	    int outputSize = Integer.parseInt(structureData[structureData.length - 1]);
	    
	    NeuralNetwork network = createNN(structureData);
	    
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
	
	public static void main(String[] args) throws IOException {
		
		NetworkTrainer trainer = new NetworkTrainer(args[0]);
		
		
	}
}