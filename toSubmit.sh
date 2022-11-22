#!/bin/bash


#to submit


mkdir submit

cp basic.sh submit/
cp efficient.sh submit/

cp src/basic/Basic.java submit/
cp src/efficient/Efficient.java submit/

sed -i 's/package basic;//g' submit/Basic.java
sed -i 's/package efficient;//g' submit/Efficient.java
#cp summary.pdf submit/
