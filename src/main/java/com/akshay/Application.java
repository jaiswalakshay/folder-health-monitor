package com.akshay;

import com.akshay.services.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private MonitoringService monitoringService;

    @Value("${secured.folder.path}")
    private String directory;

    private static ExecutorService monitoringExecutorService = Executors.newSingleThreadExecutor();

    public static void main(String... args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Directory : " + directory);
        monitoringExecutorService.submit(() -> {
            while (true) {
                TimeUnit.SECONDS.sleep(5);
                monitoringService.monitor(directory);
            }
        });

    }
}
