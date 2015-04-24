#include "NeuralNetwork.h"
#include <iostream>
#include<vector>
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <string>

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

string getFileName() {
    cout << "What is the name of your data file?" << "\n";
    string answer;
    getline(cin,answer);
    if(answer.empty()) {
        cout << "I know your keyboard isn't broken." << "\n";
        return getFileName();
    }
    return answer;
    
}

NeuralNetwork* makeNet(string structureDescription) {
    vector<int> structure;
    int buffer;
    stringstream ss(structureDescription);
    
    while(ss >> buffer) {
        structure.push_back(buffer);
    }
    
    return new NeuralNetwork(structure);
}

void fillDataVector(vector<double>& dataHolder, string line, int dataSize, 
        int startIndex) {
    double buffer;
    stringstream ss(line);
    for(int i = 0; i < startIndex; i++) {
        ss >> buffer;
    }
    for(int i = 0; i < dataSize; i++) {
        ss >> buffer;
        dataHolder.push_back(buffer);
    }
    
}

int main() {
    
    string file = getFileName();
    ifstream inputData(file);
    string line;
    
    if(inputData.is_open()) {
        getline(inputData, line);
        NeuralNetwork* net = makeNet(line);
        int inputSize = net->net[0].size();
        int outputSize = net->net.back().size();
        vector<double> inputs; vector<double> outputs;
        
        while(getline(inputData, line)) {
            fillDataVector(inputs, line, inputSize, 0);
            fillDataVector(outputs, line, outputSize, inputSize);
            net->trainNetwork(inputs, outputs);  
            inputs.clear(); outputs.clear();
            cout << net->net[2][0].outputValue << "\n";
            cout << net->net[2][1].outputValue << "\n";
            net->printTotalError();
        }
        inputData.close();
    }
   
    return 0;
}

