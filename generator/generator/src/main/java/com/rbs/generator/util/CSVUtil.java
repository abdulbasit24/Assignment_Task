//com/rbs/generator/util/CSVUtil
package com.rbs.generator.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static List<String[]> readCSV(File file) throws IOException, CsvValidationException {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        }
        return data;
    }

    public static void writeCSV(File file, List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            for (String[] line : data) {
                writer.writeNext(line);
            }
        }
    }
}



