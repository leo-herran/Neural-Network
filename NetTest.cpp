#include "NeuralNetwork.h"
#include <iostream>
#include<vector>
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <string>
#include <cassert>

using namespace std;

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

string getNetSize() {
    cout << "How many layers would you like your net to have?" << "\n";
    string answer;
    getline(cin,answer);
    if(answer.empty()) {
        cout << "I know your keyboard isn't broken." << "\n";
        return getFileName();
    }
    return answer;
    
}

NeuralNetwork* makeNet(string& structureDescription, int netSize) {
    int structure[netSize];
    int buffer;
    stringstream ss(structureDescription);
    
    int i = 0; 
    while(ss >> buffer) {
        structure[i] = buffer;
        i++;
    }
    assert(i == netSize);
    return new NeuralNetwork(structure, netSize);
    
}

void fillDataArray(double dataHolder[], string& line, int dataSize, 
        int startIndex) {
    double buffer;
    stringstream ss(line);
    for(int i = 0; i < startIndex; i++) {
        ss >> buffer;
    }
    
    for(int i = 0; i < dataSize; i++) {
        ss >> buffer;
        dataHolder[i] = buffer;
    }
}

int main() {
    
    string file = getFileName();
    
    int netSize;
    string netSizeString = getNetSize();
    istringstream convert(netSizeString);
    if ( !(convert >> netSize) ) {
        netSize = -1;
    }
    
    ifstream inputData(file);
    string line;
    
    if(inputData.is_open()) {
        getline(inputData, line);
        NeuralNetwork* net = makeNet(line, netSize);
        int inputSize = net->net[0].size();
        int outputSize = net->net.back().size();
        cout << inputSize << "\n"; cout << outputSize << "\n";
        double inputs[inputSize]; double outputs[outputSize];
        
        while(getline(inputData, line)) {
           
            fillDataArray(inputs, line, inputSize, 0);
            fillDataArray(outputs, line, outputSize, inputSize);
            net->trainNetwork(inputs, outputs);  
            
            cout << net->net[2][0].outputValue << "\n";
            cout << net->net[2][1].outputValue << "\n";
            net->printTotalError();
        }
        inputData.close();
    }
    
   
    return 0;
}

