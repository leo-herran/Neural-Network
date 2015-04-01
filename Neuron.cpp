#include "Neuron.h"
#include <cstdlib>
#include <iostream>
#include <time.h>
#include <math.h> 

Neuron::Neuron(int inDegree) {
    for(int i = 0; i < inDegree; i++) {
        double w = 0.25; //neurons initialized with arbitrary weight for now. 
        weights.push_back(w);   
    }
}

void Neuron::enterData(layer previousLayer){    
    double sum = 0.0;
    for(int i = 0; i < previousLayer.size(); i++) {
        sum += previousLayer[i].outputValue*weights[i];   
    }
    //do transfer over sum
    this->outputValue = transferFunction(sum);
}

double Neuron::transferFunction(double d) {
    //return (d/(1 + abs(d))); sigmoid
    return tanh(d);
}

void Neuron::calculateOutputDelta(double targetValue) {
    this->delta = (targetValue - outputValue)*(outputValue)*(1 - outputValue);
}

void Neuron::calculateHiddenDelta(layer& nextLayer) {
    double sumNextLayer = 0.0;
    
    for(int i = 0; i < nextLayer.size(); i++) {
        sumNextLayer += nextLayer[i].weights[i]*nextLayer[i].delta;
    }
    
    this->delta = (outputValue)*(1-outputValue)*sumNextLayer;
}

void Neuron::updateWeight(layer& previousLayer) {
    for(int i = 0; i < previousLayer.size(); i++) {
        Neuron& n = previousLayer[i];
        double w = weights[i];
        double change = learningRate * delta * n.outputValue; //*-1.0
        this->weights[i] += change;
        
    }
}

double Neuron::learningRate = 0.2;