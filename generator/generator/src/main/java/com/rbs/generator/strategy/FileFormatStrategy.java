//com/rbs/generator/strategy/FileFormatStrategy
package com.rbs.generator.strategy;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileFormatStrategy {
    List<String[]> readFile(File file) throws IOException, CsvValidationException;
    void writeFile(File file, List<String[]> data) throws IOException;
}

