#include "Neuron.h"
#include<vector>

using namespace std;

#ifndef NEURALNETWORK_H
#define	NEURALNETWORK_H

class NeuralNetwork {
    
public:
    NeuralNetwork(vector<int>& netStructure);
    /* Runs inputValues through the network, calculates the error in the total
     output of the network, and updates weights for Neurons in the network to 
     minimize this error. */
    void trainNetwork(vector<double>& inputValues, vector<double>& outputValues);
    void printTotalError();
    vector<layer> net;
    
private:
    double totalCurrentError;
    void enterData(vector<double>& input);
    
    /* Calculates total error, updates Neuron weights. */
    void backPropagate(vector<double>& targetValues);
};

#endif	

