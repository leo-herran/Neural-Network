#include "Neuron.h"
#include <cstdlib>
#include<iostream>
#include<time.h>

Neuron::Neuron(int inDegree) {
    for(int i = 0; i < inDegree; i++) {
        double w = 0.5; //neurons initialized with arbitrary weight for now. 
        weights.push_back(w);   
    }
}

void Neuron::enterData(layer previousLayer){
    double sum = 0.0;
    for(int i = 0; i < previousLayer.size(); i++) {
        sum += previousLayer[i].outputValue*weights[i];   
    }
    this->outputValue = sum;
    
}
