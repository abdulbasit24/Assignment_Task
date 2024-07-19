//com/rbs/generator/util/FileUtil
package com.rbs.generator.util;


import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    public static List<File> listFiles(String directory, String fileName) {
        File dir = new File(directory);
        return Arrays.stream(dir.listFiles((dir1, name) -> name.equals(fileName)))
                .collect(Collectors.toList());
    }
}
