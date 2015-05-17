import java.util.ArrayList;


public class NeuralNetwork {
	
	public ArrayList<Layer> net;
	public double totalCurrentError;
	
	public NeuralNetwork(ArrayList<Integer> netStructure) {
		net = new ArrayList<Layer>();
		
		for(int i = 0; i < netStructure.size(); i++) {
	        Layer currentLayer = new Layer();
	        int previousLayerSize;
	        
	        if(i == 0) {
	        	previousLayerSize = 0;
	        } else {
	        	previousLayerSize = netStructure.get(i-1);
	        }
	        
	        for(int j = 0; j < netStructure.get(i); j++) {
	            Neuron n = new Neuron(previousLayerSize);
	            currentLayer.pushNeuron(n);
	        }
	        
	        net.add(currentLayer);
	    }
		
	}
	
	void trainNetwork(ArrayList<Double> inputValues, ArrayList<Double> outputValues) {
		enterData(inputValues);
	    backPropagate(outputValues);
	}
	
	void enterData(ArrayList<Double> inputValues) {
	    for(int i = 0; i < net.get(0).size(); i++) {
	        net.get(0).getNeuron(i).outputValue = inputValues.get(i); //no transfer function on input neurons. 
	    }
	    
	    for(int i = 1; i < net.size(); i++) {
	        Layer previousLayer = net.get(i-1);
	        Layer currentLayer = net.get(i);
	        
	        for(int j = 0; j < net.get(i).size(); j++) {
	            currentLayer.getNeuron(j).calculateOutput(previousLayer);
	        }   
	    }
	}

	void backPropagate(ArrayList<Double> targetValues) { 
	    Layer outputLayer = net.get(net.size() - 1);
	    
	    double totalError = 0.0;
	    for(int i = 0; i < outputLayer.size(); i++) {
	        totalError += Math.abs(targetValues.get(i) - outputLayer.getNeuron(i).outputValue);
	    }
	    
	    totalCurrentError = totalError;
	    
	    //calculate output deltas:
	    for(int i = 0; i < outputLayer.size(); i++) {
	        outputLayer.getNeuron(i).calculateOutputDelta(targetValues.get(i));
	    }
	    
	    //calculate hidden deltas:
	    for(int i = net.size() - 2; i > 0; i--) {
	        Layer currentLayer = net.get(i);
	        Layer nextLayer = net.get(i+1);
	        for(int j = 0; j < currentLayer.size(); j++) {
	            currentLayer.getNeuron(j).calculateHiddenDelta(nextLayer, j);
	        }
	    }
	    
	    //update weights:
	    for(int i = net.size() - 1; i > 0; i--) {
	        Layer currentLayer = net.get(i);
	        for(int j = 0; j < currentLayer.size(); j++) {
	        	currentLayer.getNeuron(j).updateWeight(net.get(i-1));
	        }
	    }
	}
	
	void printTotalError() {
		
		System.out.println(totalCurrentError);
	}
	
	
	

}
