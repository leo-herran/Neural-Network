#include "Neuron.h"
#include<vector>

using namespace std;

#ifndef NEURALNETWORK_H
#define	NEURALNETWORK_H

class NeuralNetwork {
    
public:
    NeuralNetwork(vector<int>& netStructure);
    vector<double> enterData(vector<double>& input);
    vector<double> backPropagate(vector<double>& targetValues);
    vector<layer> net;
    
private:
     
};

#endif	

