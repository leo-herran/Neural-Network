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
    
private:
    vector<double> weights; //make private
    
};

#endif

