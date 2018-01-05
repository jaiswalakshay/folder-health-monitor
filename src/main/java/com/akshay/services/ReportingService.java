package com.akshay.services;

import com.akshay.domain.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReportingService {
    private final static Logger logger = LoggerFactory.getLogger(ReportingService.class);

    public void printReport(Report report) {
        logger.info("Report :" + report.toString());
        logger.info("Time :" + new Date().toString());
    }
}
