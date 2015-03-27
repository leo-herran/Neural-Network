#include "Neuron.h"
#include<vector>

#ifndef NEURALNETWORK_H
#define	NEURALNETWORK_H

class NeuralNetwork {
    
public:
    NeuralNetwork(vector<int> netStructure);
    vector<double> enterData(vector<double> input);
    vector<layer> net;
private:
     
};

#endif	

