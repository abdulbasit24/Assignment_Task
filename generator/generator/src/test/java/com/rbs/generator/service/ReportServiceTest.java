// com/rbs/generator/service/ReportServiceTest.java
package com.rbs.generator.service;

import com.opencsv.exceptions.CsvValidationException;
import com.rbs.generator.util.CSVUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
    "report.input-directory=C:/Users/abdulbasit/Desktop/RBS_Project/generator/generator/data/",
    "report.output-directory=C:/Users/abdulbasit/Desktop/RBS_Project/generator/generator/data/",
    "report.schedule=0 0 * * * *"
})
public class ReportServiceTest {

    @Mock
    private TransformationService transformationService;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void setup() {
        // Manually set the values of the fields using ReflectionTestUtils
        ReflectionTestUtils.setField(reportService, "inputDirectory", "C:/Users/abdulbasit/Desktop/RBS_Project/generator/generator/data/");
        ReflectionTestUtils.setField(reportService, "outputDirectory", "C:/Users/abdulbasit/Desktop/RBS_Project/generator/generator/data/");
        ReflectionTestUtils.setField(reportService, "reportSchedule", "0 0 * * * *");
    }

    @Test
    public void testGenerateReports() throws IOException, CsvValidationException {
        List<String[]> mockTransformedData = Collections.singletonList(new String[]{"data1", "data2"});
        
        when(transformationService.transform(any(File.class), any(File.class), any(File.class)))
                .thenReturn(mockTransformedData);

        try (MockedStatic<CSVUtil> mockedCSVUtil = mockStatic(CSVUtil.class)) {
            mockedCSVUtil.when(() -> CSVUtil.writeCSV(any(File.class), anyList())).thenAnswer(invocation -> null);

            reportService.generateReports();

            verify(transformationService, times(1)).transform(any(File.class), any(File.class), any(File.class));
            mockedCSVUtil.verify(() -> CSVUtil.writeCSV(any(File.class), eq(mockTransformedData)), times(1));
        }
    }
}
