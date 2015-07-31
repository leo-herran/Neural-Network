#!/bin/bash

letterData="characterRecognitionFiles/5letterData.txt"
letterMapping="characterRecognitionFiles/letterMappingData.txt"
input=""

#print the nine numbers that the user inputs in a 3-by-3 grid. 
printInput() {
  let index=1
  for number in $input; do
    echo -n $number
    if [ $index -eq 3 ] || [ $index -eq 6 ]; then
      echo
    fi
    let index="$index + 1"
  done
}

#get nine numbers from the user.
for i in `seq 3`; do
  read -p "Enter a row. " row
  row=`echo $row | sed -e 's/./& /g'` #put a space between input numbers.
  input=$input" $row"
done

#convert the nine boolean values into input for the network.
shiftedInput=""
for number in $input; do
  nextNum=""
  let numValue=$number
  if [ $numValue -eq 1 ]; then
    nextNum="5.0"
  else
    nextNum="-5.0"
  fi
  shiftedInput=$shiftedInput" $nextNum"
done

echo $shiftedInput > letterInput.txt

java characterRecognizer $letterData $letterMapping letterInput.txt > output.txt

printInput
echo -n " is the letter "
cat output.txt

rm letterInput.txt
rm output.txt
