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

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private MonitoringService monitoringService;

    @Value("${secured.folder.path}")
    private String directory;

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String... args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Dire : " + directory);

        monitoringService.monitor(directory);


    }

//    @Override
//    public void init(DaemonContext daemonContext) throws DaemonInitException, Exception {
//        logger.debug("Application initialized with arguments {}.", daemonContext.getArguments());
//    }
//
//    @Override
//    public void start() throws Exception {
//        logger.info("Starting up daemon.");
//        this.executorService.execute(new Runnable() {
//            CountDownLatch latch = new CountDownLatch(1);
//            public void run() {
//                try {
//                    latch.await();
//                } catch (InterruptedException e) {
//                    logger.debug("Thread interrupted, probably means we're shutting down now.");
//                }
//            }
//        });
//    }
//
//    @Override
//    public void stop() throws Exception {
//        logger.info("Stopping Application.");
//        this.executorService.shutdown();
//    }
//
//    @Override
//    public void destroy() {
//        logger.info("Destroying Application.");
//    }
}
