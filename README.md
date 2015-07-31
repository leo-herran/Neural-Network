# Neural-Network

In the most general sense, an artificial neural network is a construct containing a set of interconnected neurons, each of which accepts multiple inputs from other neurons and outputs a value to one or more places. Every connection between two neurons is associated with a real number, called the weight--this determines the strength of the connection. A networks have one or more input

The neural network implemented here is of the feedforward variety, meaning there are no cycles in the connections between neurons (data is always fed forward toward the output). It uses the backpropagation algorithm to learn patterns from its input.  

One way I was successfully able to apply my implementation was rudimentary ASCII character recognition. I used nine boolean inputs to represent characters. For example, the input values
<code>[1, 1, 1, 0, 1, 0, 0, 1, 0]</code> would map to the letter "T" because when the nine input values are viewed in a 3-by-3 grid they resemble a capital T:

![alt text](http://i.imgur.com/Rp98Grf.png?1 "Letter mapping scheme.")

Then network then outputs three values which represent the density of 1s in each row. For example, when given the inputs for the letter T the network outputs <code>[1, 0, 0]</code> because there are three 1s in the top row and only one 1 in the bottom two rows. As of now the network converges on a training set containing five characters:

![alt text](http://i.imgur.com/SZujtG6.png?1)


