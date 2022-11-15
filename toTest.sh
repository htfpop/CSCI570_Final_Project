#!/bin/bash

j=1
for i in $(ls ProjectRequirements/datapoints/*.txt)
do
STRING_NAME=out$(j).txt    
./submit/basic.sh < $i $STRING_NAME
j=$j+1
done 


