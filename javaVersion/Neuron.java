import java.util.ArrayList;
import java.util.Random;


public class Neuron {

	private ArrayList<Double> weights;
	public double outputValue;
	private double delta;
	private static double learningRate = .4;
	
	public Neuron(int inDegree) {
		weights = new ArrayList<Double>();
		Random rand = new Random();
	    for(int i = 0; i < inDegree; i++) {
	        weights.add(rand.nextDouble());
	    }
	    
	}
	
	void calculateOutput(Layer previousLayer) {    
	    double sum = 0.0;
	    for(int i = 0; i < previousLayer.size(); i++) {
	        sum += previousLayer.getNeuron(i).outputValue*weights.get(i);   
	    }
	    //do transfer over sum
	    outputValue = transferFunction(sum);
	}

	double transferFunction(double d) {
	    return Math.tanh(d);
	}

	void calculateOutputDelta(double targetValue) {
	    double diff = targetValue - outputValue;
	    delta = diff*(1 - outputValue*outputValue);
	}

	void calculateHiddenDelta(Layer nextLayer, int index) {
	    double sumNextLayer = 0.0;
	    for(int i = 0; i < nextLayer.size(); i++) {
	        sumNextLayer += nextLayer.getNeuron(i).weights.get(index)*nextLayer.getNeuron(i).delta;
	    }
	    
	    delta = (1 - outputValue*outputValue)*sumNextLayer;
	}

	void updateWeight(Layer previousLayer) {
	    for(int i = 0; i < previousLayer.size(); i++) {
	        Neuron n = previousLayer.getNeuron(i);
	        double change = learningRate * delta * n.outputValue;
	        weights.set(i, weights.get(i) + change);
	    }
	}
	
	
}


