#include <vector>

using namespace std;
class Neuron;
typedef vector<Neuron> layer;

#ifndef NEURON_H
#define	NEURON_H

class Neuron {
public:
    Neuron(int inDegree);
    double outputValue;
    void enterData(layer previousLayer);
    void calculateOutputDelta(double targetValue);
    void calculateHiddenDelta(layer& nextLayer, int index);
    void updateWeight(layer& previousLayer);
    
 
    static double learningRate;
    static double changeInWeight;
    vector<double> weights; //make private
    static double transferFunction(double d);
    double delta; 
    
    private:
};

#endif

