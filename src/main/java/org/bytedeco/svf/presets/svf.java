package org.bytedeco.svf.presets;

import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.LoadEnabled;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(
    target = "org.bytedeco.svf",
    global = "org.bytedeco.svf.global.svf",
    value = {
      @Platform(
        value = {
            "linux-x86",
            "linux-x86_64",
            "macosx-x86_64",
            "windows-x86",
            "windows-x86_64"
          },
        include = {
                "SVF-C/Types.h",
                "SVF-C/Utils.h"
        },
        compiler = "cpp11",
        link = "svf",
        preload = "libsvf"
      )
    }
)

public class svf implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("std::string").annotations("@StdString").valueTypes("BytePointer", "String").pointerTypes("@Cast({\"char*\", \"std::string*\"}) BytePointer"))
        .put(new Info("std::vector<std::string>").pointerTypes("StringVector").define())
                .put(new Info("OpaqueDDAPass").pointerTypes("SVFDDAPass"))
                .put(new Info("SVFDDAPass").valueTypes("SVFDDAPass").pointerTypes("@ByPtrPtr SVFDDAPass", "@Cast(\"SVFDDAPass*\") PointerPointer"))
                .put(new Info("OpaqueSVFModule").pointerTypes("SVFSVFModule"))
                .put(new Info("SVFSVFModule").valueTypes("SVFSVFModule").pointerTypes("@ByPtrPtr SVFSVFModule", "@Cast(\"SVFSVFModule*\") PointerPointer"));
    }
}
