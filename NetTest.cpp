#include "NeuralNetwork.h"
#include <iostream>
#include<vector>
#include <cstdlib>

using namespace std;

void runInputOne(NeuralNetwork& n) {
    vector<double> d;
    d.push_back(1.0);
    d.push_back(0.0);
    
    vector<double> target;
    target.push_back(1.0);
    
    n.trainNetwork(d, target);
    
    double out = n.net[2][0].outputValue;
    cout << "output: " << out << "\n";   
    cout << "error: " << (1.0 - out) << "\n";
}


void runInputTwo(NeuralNetwork& n) {
    vector<double> d;
    d.push_back(0.0);
    d.push_back(1.0);
    
    vector<double> target;
    target.push_back(0.0);
    
    n.trainNetwork(d, target);
    
    double out = n.net[2][0].outputValue;
    cout << "output: " << out << "\n";
    cout << "error: " << out << "\n";
    
}

int main() {
    
    vector<int> structure;
    structure.push_back(2);
    structure.push_back(4);
    structure.push_back(1);
    NeuralNetwork n(structure);

    for(int i = 0; i < 100; i++) {
        runInputOne(n);
        runInputTwo(n);

    }
   
    return 0;
}

