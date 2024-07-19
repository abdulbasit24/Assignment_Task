//com/rbs/generator/controller/ReportController
package com.rbs.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.generator.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport() {
        reportService.generateReports();
        return ResponseEntity.ok("Report generation triggered.");
    }
}
