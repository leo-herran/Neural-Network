#include "Neuron.h"
#include<vector>

using namespace std;

#ifndef NEURALNETWORK_H
#define	NEURALNETWORK_H

class NeuralNetwork {
    
public:
    NeuralNetwork(int netStructure[], int numberOfLayers);
    
    /* Runs inputValues through the network, calculates the error in the total
     output of the network, and updates weights for Neurons in the network to 
     minimize this error. */
    void trainNetwork(double inputValues[], double outputValues[]);
    
    void printTotalError();
    vector<layer> net;
    
private:
    double totalCurrentError;
    void enterData(double input[]);
    
    /* Calculates total error, updates Neuron weights. */
    void backPropagate(double targetValues[]);
};

#endif	

