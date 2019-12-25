#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" svf
    popd
    exit
fi


# mkdir -p cppbuild
# cd cppbuild
mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`

if [ ! -d "svf/" ];then
    git clone https://github.com/EboYu/SVF.git svf
fi


$CMAKE -DCMAKE_INSTALL_PREFIX=$INSTALL_PATH svf/
make -j8
make install

cd ../..
