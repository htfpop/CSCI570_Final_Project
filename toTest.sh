#!/bin/bash
PWD=pwd
cd submit
j=1
for i in $(ls ../ProjectRequirements/datapoints/in*.txt)
do
STRING_NAME=output_basic$j.txt    
STRING_NAME2=output_eff$j.txt
./basic.sh  $i $STRING_NAME
./efficient.sh $i $STRING_NAME2
j=$((j+1))
done 
cd $PWD


