#!/bin/bash

#Input: a file containing 3 by 3 blocks of 1s and 0s followed by
#one line containing either a 1 or a 0. The 3x3 blocks represent the input
#data to the neural network and the boolean after them is the target output
#that specifies whether the block contains a 2x2 dot.

#sample input:
#110
#110
#000
#1

filename="$1"

touch dataInput.txt
rm dataInput.txt
touch dataInput.txt

echo "9 9 9 1" >> dataInput.txt
echo "60" >> dataInput.txt

let index=1

while read -r line; do
  if [ $index -eq 4 ]; then
    echo $line >> dataInput.txt
    let index=0
  else
    line=`echo $line | sed -e 's/./& /g'`
    for letter in $line; do
      echo -n "$letter " >> dataInput.txt
    done
  fi
  let index="$index + 1"
done < "$filename"
