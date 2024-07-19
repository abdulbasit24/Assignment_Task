//com/rbs/generator/service/ReportService
package com.rbs.generator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rbs.generator.util.CSVUtil;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;
import java.util.List;
//import java.nio.file.Path;
//import java.nio.file.Paths;


@Service
public class ReportService {

    @Value("${report.input-directory}")
    private String inputDirectory;

    @Value("${report.output-directory}")
    private String outputDirectory;

    @Value("${report.schedule}")
    private String reportSchedule;

    private final TransformationService transformationService;

    public ReportService(TransformationService transformationService) {
        this.transformationService = transformationService;
    }

    @Scheduled(cron = "${report.schedule}")
    public void generateReports() {
        File inputFile = new File(inputDirectory);
        File referenceFile = new File(inputDirectory.replace("input.csv", "reference.csv"));
        File outputFile = new File(outputDirectory, "output.csv");

        if (!inputFile.exists() || !referenceFile.exists()) {
            System.err.println("Input or reference file not found!");
            return;
        }

        try {
        	List<String[]> transformedData = transformationService.transform(inputFile, referenceFile, outputFile);
            CSVUtil.writeCSV(new File(outputDirectory, "output.csv"), transformedData);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

