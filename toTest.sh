#!/bin/bash
PWD=pwd
cd submit
for i in $(ls ../ProjectRequirements/datapoints/in*.txt)
do
BASEN=$(echo $(basename $i) |sed 's/.txt//g')
STRING_NAME=output_basic_$BASEN.txt    
STRING_NAME2=output_eff_$BASEN.txt
./basic.sh  $i $STRING_NAME
./efficient.sh $i $STRING_NAME2
done 
cd $PWD


