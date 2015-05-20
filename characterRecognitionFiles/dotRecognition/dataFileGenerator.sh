#!/bin/bash

filename="$1"

touch dataInput.txt

echo "9 9 7 1" >> dataInput.txt
echo "20" >> dataInput.txt

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
