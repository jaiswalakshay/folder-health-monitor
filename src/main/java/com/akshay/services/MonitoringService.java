package com.akshay.services;

import com.akshay.domain.Report;
import com.akshay.utils.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class MonitoringService {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);


    @Autowired
    private SecurityService securityService;

    @Autowired
    private FileUtility fileUtility;

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private ArchivalService archivalService;

    @Value("${max.folder.size.mb}")
    long value;

    public void monitor(String directory) {
        logger.debug("Monitoring service start");

        Report report = new Report();

        report.setDirectory(new File(directory));

        List<File> deletedFiles = securityService.securityCheck(directory);
        report.setDeletedFiles(deletedFiles);

        List<File> archivedFiles = archivalService.archive(directory);
        report.setArchivedFiles(archivedFiles);

        reportingService.printReport(report);


    }

}
