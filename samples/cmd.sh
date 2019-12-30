#!/bin/bash

cp ../target/svf.jar .
cp ../target/svf-linux-x86_64.jar .
javac -cp .:javacpp.jar:svf.jar:svf-linux-x86_64.jar Test.java
#java -cp .:javacpp.jar:svf.jar:svf-linux-x86_64.jar Test -h
java -cp .:javacpp.jar:svf.jar:svf-linux-x86_64.jar Test -cxt -query=all -print-query-pts --dump-top-pts -stat=false --dump-txt-pag /home/yinbo/disk/workspace/SUPA_RESULT/field-mem2reg.ll
