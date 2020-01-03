# javaSVF

    
Intro
---------
SVF is a static tool that enables scalable and precise interprocedural dependence analysis for C and C++ programs (https://github.com/SVF-tools/SVF).

This respository provides Java APIs of SVF implemented by JavaCPP.

    
Perform
---------

1. We insert 'SHARED' and link svf with LLVMCud (which can make libsvf.so can be invokded directly) (in lib/CMakeLists.txt) to generate libsvf.so

2. We use llvm/Support/CBindingWrapping.h to write some C interfaces of SVF in the include/SVF-C/

3. We define the configuration for Java swrapping (src/main/java/org/cellscope/svf/presets/svf.java), in which we include C headers and define the mapping between C types and Java types by infomap

4. Perform 
    ```mvn clean install```, get the svf.jar and svf-linux-x86_64.jar. Using those two jar packages and javacpp(1.5.2) package, we invoke SVF.
    
    
Issue
---------
When using binding package, before we define an JavaCPP object (like PointerPointer, BytePointer), we need to invoke some functions in svf.jar (like starting()). If not, an error about Pointer Allocation will be exposed. Still not understand!