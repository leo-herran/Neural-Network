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

    vector<double> tar;
    tar.push_back(1.1);tar.push_back(1.1);
    
    double error = (1.1 - n.net[1][0].outputValue) + (1.1 - n.net[1][0].outputValue);
    cout << "error: " << error << "\n";
    
    n.backPropagate(tar);
    
    n.enterData(d);
    
    error = (1.1 - n.net[1][0].outputValue) + (1.1 - n.net[1][0].outputValue);
    cout << "error: " << error << "\n";
    
    vector<double> tar2;
    tar2.push_back(1.1);tar2.push_back(1.1);
    n.backPropagate(tar2);
//    
    n.enterData(d);
    
    error = (1.1 - n.net[1][0].outputValue) + (1.1 - n.net[1][0].outputValue);
    cout << "error: " << error << "\n";
    
    //output: 
    //error: 1.2
    //error: 1.08
    //error: 0.97
    //it's going down!!!

    return 0;
}

