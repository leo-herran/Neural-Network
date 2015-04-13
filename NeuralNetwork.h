#include "Neuron.h"
#include<vector>

using namespace std;

#ifndef NEURALNETWORK_H
#define	NEURALNETWORK_H

class NeuralNetwork {
    
public:
    NeuralNetwork(vector<int>& netStructure);
    void trainNetwork(vector<double>& inputValues, vector<double>& outputValues);
    void printTotalError();
    vector<layer> net;
    
private:
    double totalCurrentError;
    void enterData(vector<double>& input);
    void backPropagate(vector<double>& targetValues);
};

#endif	

