

def makeRandomMatrix(x, y):
    matrix = [];
    for i in range(x):
        matrix.append([]); 
        for j in range(y):
            matrix[i].append(1.0);

    return matrix;

class NeuralNetwork:

    def __init__(self, netStructure):

        self.netStructure = netStructure;
        self.network = []; 
        self.network.append(Layer(netStructure[0], 0)); 
        for i in range(1, len(netStructure)):
            self.network.append(Layer(netStructure[i], netStructure[i-1]));

    def feedForward(self, inputs):

        #set output values for input layer.
        for i in range(self.netStructure[0]):
            self.network[0][i] = inputs[i];

        for i in range(1, len(self.network)):
            cur = self.network[i];
            prev = self.network[i-1];
            for j in range(len(cur)):
                sum = 0.0;
                for k in range(len(prev)):
                    sum = sum + cur.weights[j][k]*cur.outputs[j];

                cur.outputs[j] = sigmoid(sum);




class Layer:

    #for hidden/output layer initialization
    def __init__(self, size, previousSize):

        if previousSize == 0:
            #input layer
            self.weights = [[]]; #input layer is not weighted
        else:
            #hidden/output layer
            self.weights = makeRandomMatrix(size, previousSize); 

        self.outputs = [1.0]*size;





m = makeRandomMatrix(2, 3);
n = NeuralNetwork([2, 4, 2]);
print(n);
