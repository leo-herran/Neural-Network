#!/bin/bash

letterData="characterRecognitionFiles/5letterData.txt"
letterMapping="characterRecognitionFiles/letterMappingData.txt"
input=""

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

for i in `seq 3`; do
  read -p "Enter a row. " row
  row=`echo $row | sed -e 's/./& /g'` #put a space between input numbers.
  input=$input" $row"
done

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
