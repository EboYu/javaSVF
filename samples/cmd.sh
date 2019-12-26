#!/bin/bash

cp ../target/svf.jar .
cp ../target/svf-linux-x86_64.jar .
javac -cp .:javacpp.jar:svf.jar:svf-linux-x86_64.jar Test.java
java -cp .:javacpp.jar:svf.jar:svf-linux-x86_64.jar Test -cxt -query=all -print-query-pts /home/yinbo/disk/workspace/SUPA_RESULT/example-mem2reg.ll
