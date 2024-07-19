//com/rbs/generator/service/FileProcessingService
package com.rbs.generator.service;

import com.rbs.generator.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileProcessingService {

    @Value("${report.input-directory}")
    private String inputDirectory;

    public List<File> getFeedFiles() {
        return FileUtil.listFiles(inputDirectory, "input.csv");
    }

    public List<File> getReferenceFiles() {
        return FileUtil.listFiles(inputDirectory, "reference.csv");
    }
}

