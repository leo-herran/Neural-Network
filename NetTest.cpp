#include "NeuralNetwork.h"
#include <iostream>
#include<vector>
#include <cstdlib>

using namespace std;

void runStuff(NeuralNetwork& n) {
    cout << "TEST" << "\n";
    vector<double> d;
    d.push_back(1.0);
    d.push_back(1.0);
    
    vector<double> tar;
    tar.push_back(1.0);
    
    cout << "w1 " << n.net[1][0].weights[0] << "\n";
    cout << "w2 " << n.net[1][0].weights[1] << "\n";
    cout << "out " << n.net[1][0].outputValue << "\n";
    
    n.trainNetwork(d, tar);
    
    cout << "w1 " << n.net[1][0].weights[0] << "\n";
    cout << "w2 " << n.net[1][0].weights[1] << "\n";
    cout << "out " << n.net[1][0].outputValue << "\n";    
}

int main() {
    
    vector<int> structure;
    structure.push_back(2);
    structure.push_back(1);
    NeuralNetwork n(structure);

    for(int i = 0; i < 100; i++) {
        runStuff(n);
    }
   
    return 0;
}

