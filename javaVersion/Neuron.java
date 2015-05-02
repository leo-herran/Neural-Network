import java.util.ArrayList;
import java.util.Random;


public class Neuron {

	private ArrayList<Weight> weights; /* contains weights for connections from all neurons in the previous
	layer as well as the previous change in weight for all those neurons. */ 
	
	public double outputValue; 
	private double delta; //partial of error with respect to weights; used to calculate change in weight.  
	private static double momentum = .3; //how much the old change in weight influences the new change. 
	private static double learningRate = .2; 
	
	public Neuron(int inDegree) {
		weights = new ArrayList<Weight>();
		Random rand = new Random();
	    for(int i = 0; i < inDegree; i++) {
	        weights.add(new Weight(rand.nextDouble(), 0.0));
	    }
	}
	
	void calculateOutput(Layer previousLayer) {    
	    double sum = 0.0;
	    for(int i = 0; i < previousLayer.size(); i++) {
	        sum += previousLayer.getNeuron(i).outputValue*weights.get(i).weight;   
	    }
	    //do transfer over sum
	    outputValue = transferFunction(sum);
	}

	double transferFunction(double d) {
	    return Math.tanh(d);
	}

	void calculateOutputDelta(double targetValue) {
	    double diff = targetValue - outputValue;
	    delta = diff*(1.0 - outputValue*outputValue);
	}

	void calculateHiddenDelta(Layer nextLayer, int index) {
	    double sumNextLayer = 0.0;
	    for(int i = 0; i < nextLayer.size(); i++) {
	        sumNextLayer += nextLayer.getNeuron(i).weights.get(index).weight*nextLayer.getNeuron(i).delta;
	    }
	    
	    delta = (1.0 - outputValue*outputValue)*sumNextLayer;
	}

	void updateWeight(Layer previousLayer) {
		double change;
	    for(int i = 0; i < previousLayer.size(); i++) {
	        Neuron n = previousLayer.getNeuron(i);
	        change = learningRate * delta * n.outputValue + momentum * weights.get(i).previousChangeInWeight;
	        
	        //add change to weight for this neuron, set previousChangeIW to change just computed
	        weights.set(i, new Weight(weights.get(i).weight + change, change)); 
	    }
	    
	}
	
	final class Weight {
		
		public double weight;
		public double previousChangeInWeight;
		
		public Weight(double weight, double previousChange) {
			this.weight = weight;
			this.previousChangeInWeight = previousChange;
		}
	}
	
	
}


