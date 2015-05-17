import java.util.ArrayList;

public class Layer {
	
		private ArrayList<Neuron> neurons;
		
		public Layer() {
			neurons = new ArrayList<Neuron>();
		}
		
		Neuron getNeuron(int index) {
			return neurons.get(index);
		}
		
		void pushNeuron(Neuron n) {
			neurons.add(n);
		}
		
		int size() {
			return neurons.size();
		}
	}