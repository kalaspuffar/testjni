package org.example;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;

public class LibFOORunner {
    public static void main(String[] args) {
        Linker linker = Linker.nativeLinker();
        try (Arena arena = Arena.ofConfined()) {
            SymbolLookup fooLib = SymbolLookup.libraryLookup(
                "/home/woden/github/testjni/libfoo.so",
                arena
            );
            MethodHandle fooFunc = linker.downcallHandle(
                fooLib.find("foo").get(),
                FunctionDescriptor.ofVoid()
            );
            fooFunc.invoke();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
