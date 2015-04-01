#include "NeuralNetwork.h"
#include<vector>
#include<cassert>
#include<iostream>

using namespace std;

NeuralNetwork::NeuralNetwork(vector<int>& netStructure) {
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

vector<double> NeuralNetwork::enterData(vector<double>& input) {
    
    for(int i = 0; i < input.size(); i++) {
        net[0][i].outputValue = input[i]; //no transfer function on input neurons. 
    }
    
    for(int i = 1; i < net.size(); i++) {
        layer previousLayer = net[i-1];
        for(int j = 0; j < net[i].size(); j++) {
            net[i][j].enterData(previousLayer);
        }   
    }
}

vector<double> NeuralNetwork::backPropagate(vector<double>& targetValues) { 
    layer& outputLayer = net.back();
    
    double totalError = 0.0;
    for(int i = 0; i < outputLayer.size(); i++) {
        totalError += targetValues[i] - outputLayer[i].outputValue;
    }
    
    
    //calculate output deltas:
    for(int i = 0; i < outputLayer.size(); i++) {
        outputLayer[i].calculateOutputDelta(targetValues[i]);
    }
    
    //calculate hidden deltas:
    for(int i = net.size() - 2; i > 0; i--) {
        for(int j = 0; j < net[i].size(); j++) {
            net[i][j].calculateHiddenDelta(net[i+1]);
        }
    }
    
    //update weights:
    
    for(int i = net.size() - 1; i > 0; i--) {
        layer l = net[i];
        for(int j = 0; j < l.size(); j++) {
            net[i][j].updateWeight(net[i-1]);
        }
    }
    

    
}