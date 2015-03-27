#include "NeuralNetwork.h"
#include<vector>
#include<cassert>
#include<iostream>

NeuralNetwork::NeuralNetwork(vector<int> netStructure) {
    int numberLayers = netStructure.size();
    for(int i = 0; i < numberLayers; i++) {
        layer l;
        int previousLayerSize;
        (i == 0 ? previousLayerSize = 0 : previousLayerSize = netStructure[i-1]);
        
        for(int j = 0; j < netStructure[i]; j++) {
            Neuron n(previousLayerSize);
            l.push_back(n);
        }
        net.push_back(l);
    }
}

vector<double> NeuralNetwork::enterData(vector<double> input) {
    
    for(int i = 0; i < input.size(); i++) {
        net[0][i].outputValue = input[i];
    }
    
    for(int i = 1; i < net.size(); i++) {
        layer previousLayer = net[i-1];
        for(int j = 0; j < net[i].size(); j++) {
            net[i][j].enterData(previousLayer);
        }   
    }
}