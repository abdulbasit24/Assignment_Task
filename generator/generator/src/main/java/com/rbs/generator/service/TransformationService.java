//com/rbs/generator/service/TransformationService
package com.rbs.generator.service;
import org.springframework.stereotype.Service;

import com.opencsv.exceptions.CsvValidationException;
import com.rbs.generator.util.CSVUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class TransformationService {

    public List<String[]> transform(File inputFile, File referenceFile, File outputFile) throws IOException, CsvValidationException {
        List<String[]> inputData = CSVUtil.readCSV(inputFile);
        List<String[]> referenceData = CSVUtil.readCSV(referenceFile);
        
        List<String[]> transformedData = new ArrayList<>();
        
        // Add the header row
        String[] header = {"outfield1", "outfield2", "outfield3", "outfield4", "outfield5"};
        transformedData.add(header);

        for (String[] feedRow : inputData) {
            for (String[] refRow : referenceData) {
                if (feedRow[5].equals(refRow[0]) && feedRow[6].equals(refRow[2])) {
                    String[] outputRow = new String[5];
                    outputRow[0] = feedRow[0] + feedRow[1];
                    outputRow[1] = refRow[1];
                    outputRow[2] = refRow[3] + refRow[4];
                    outputRow[3] = String.valueOf(Double.parseDouble(feedRow[3]) * Math.max(Double.parseDouble(feedRow[4]), Double.parseDouble(refRow[5])));
                    outputRow[4] = String.valueOf(Math.max(Double.parseDouble(feedRow[4]), Double.parseDouble(refRow[5])));
                    transformedData.add(outputRow);
                }
            }
        }
        
        CSVUtil.writeCSV(outputFile, transformedData);
        return transformedData;
    }
}