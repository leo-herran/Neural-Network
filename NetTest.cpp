#include "NeuralNetwork.h"
#include <iostream>
#include<vector>
#include <cstdlib>

using namespace std;

int main() {
    
    vector<int> structure;
    structure.push_back(2);
    structure.push_back(2);
    NeuralNetwork n(structure);

    vector<double> d;
    d.push_back(1.0);
    d.push_back(1.0);
    n.enterData(d);

    std::cout << "output of inner node 1: " << n.net[0][0].outputValue << "\n";
    std::cout << "output inner node 2: " << n.net[0][1].outputValue << "\n";
    std::cout << "output of final node 1: " << n.net[1][0].outputValue << "\n";
    std::cout << "output of final node 2: " << n.net[1][1].outputValue << "\n";
    
    return 0;
}

