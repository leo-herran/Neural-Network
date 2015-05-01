#include "NeuralNetwork.h"
#include<vector>
#include<cassert>
#include<iostream>
#include<stdlib.h>

using namespace std;

NeuralNetwork::NeuralNetwork(int netStructure[], int numberOfLayers) {
    for(int i = 0; i < numberOfLayers; i++) {
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

void NeuralNetwork::trainNetwork(double inputValues[], double outputValues[]) {
    enterData(inputValues);
    backPropagate(outputValues);
}

void NeuralNetwork::enterData(double inputValues[]) {
    
    for(int i = 0; i < net[0].size(); i++) {
        net[0][i].outputValue = inputValues[i]; //no transfer function on input neurons. 
    }
    
    for(int i = 1; i < net.size(); i++) {
        layer previousLayer = net[i-1];
        for(int j = 0; j < net[i].size(); j++) {
            net[i][j].calculateOutput(previousLayer);
        }   
    }
}

void NeuralNetwork::backPropagate(double targetValues[]) { 
    layer& outputLayer = net.back();
    
    double totalError = 0.0;
    for(int i = 0; i < outputLayer.size(); i++) {
        totalError += abs(targetValues[i] - outputLayer[i].outputValue);
    }
    this->totalCurrentError = totalError;
    
    //calculate output deltas:
    for(int i = 0; i < outputLayer.size(); i++) {
        outputLayer[i].calculateOutputDelta(targetValues[i]);
    }
    
    //calculate hidden deltas:
    for(int i = net.size() - 2; i > 0; i--) {
        layer& currentLayer = net[i];
        layer& nextLayer = net[i+1];
        for(int j = 0; j < currentLayer.size(); j++) {
            net[i][j].calculateHiddenDelta(nextLayer, j);
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

void NeuralNetwork::printTotalError() {
    std::cout << totalCurrentError << "\n";
    
}