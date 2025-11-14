package com.dsp_calculator.tools;

import com.google.javascript.jscomp.*;
import java.nio.file.*;
import java.util.Collections;
import java.io.IOException;
import java.util.stream.Stream;

public class JsCompressor {
    public static void main(String[] args) throws Exception {
        Path sourceDir = Paths.get("src/main/resources/static/js");
        Path targetDir = Paths.get("src/main/resources/static/js");

        Files.createDirectories(targetDir);

        try (Stream<Path> files = Files.walk(sourceDir)) {
            files.filter(p -> p.toString().endsWith(".js") && !p.toString().endsWith(".min.js"))
                 .forEach(p -> {
                     try {
                         Path output = targetDir.resolve(sourceDir.relativize(p).toString().replace(".js", ".min.js"));
                         Files.createDirectories(output.getParent());

                         // Using Closure Compiler API to avoid System.exit()
                         Compiler compiler = new Compiler();
                         CompilerOptions options = new CompilerOptions();
                         CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
                         options.setLanguageIn(CompilerOptions.LanguageMode.ECMASCRIPT_NEXT);
                         options.setLanguageOut(CompilerOptions.LanguageMode.ECMASCRIPT5);

                         SourceFile inputFile = SourceFile.fromFile(p.toString());
                         SourceFile extern = SourceFile.fromCode("externs.js", "");

                         Result result = compiler.compile(Collections.singletonList(extern),
                                                          Collections.singletonList(inputFile),
                                                          options);

                         if (result.success) {
                             Files.write(output, compiler.toSource().getBytes());
                             System.out.println("Compressed: " + p + " --> " + output);
                         } else {
                             System.err.println("Failed: " + p);
                         }

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
        }
    }
}
