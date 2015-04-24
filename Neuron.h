#include <vector>

using namespace std;
class Neuron;
typedef vector<Neuron> layer;

#ifndef NEURON_H
#define	NEURON_H

class Neuron {
public:
    /* Constructs a Neuron that takes in values from inDegree Neurons in the 
     previous layer. */
    Neuron(int inDegree);
    
    double outputValue;
    void calculateOutput(layer previousLayer);
    
    /* Calculates the delta value for Neurons in the output layer. */
    void calculateOutputDelta(double targetValue);
    
    /* Calculates the delta value for Neurons in a hidden layer. */
    void calculateHiddenDelta(layer& nextLayer, int index);
    void updateWeight(layer& previousLayer);
    
    private:
    static double learningRate;
    static double changeInWeight;
    double* weights; 
    
    /* Partial derivative of the error with respect to weights from previous 
     * Neurons. Used in the back-propagation algorithm to update the weight, 
     * which minimizes error. */
    double delta;
    
    /* Function through which the output value of each Neuron is passed. */
    static double transferFunction(double d);
    
    /* Returns a random value between 0 and 1. */
    static double getRandomWeight();
    
};

#endif

