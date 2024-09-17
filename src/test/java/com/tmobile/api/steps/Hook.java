package com.tmobile.api.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Hook {
    public static Logger logger;

    @Before
    public void beforeScenario(@NotNull Scenario scenario) {
        startLogging();
        logger.info("==========================================================================================");
        logger.info("Scenario name: " + scenario.getName());
        logger.info("==========================================================================================");
    }

    @After
    public void afterScenario(Scenario scenario) {
        logger.info("==========================================================================================");
        if (scenario.isFailed()) {
            logger.error("Scenario name: " + scenario.getName() + " finished with result: " + scenario.getStatus());
        } else {
            logger.info("Scenario name: " + scenario.getName() + " finished with result: " + scenario.getStatus());
        }
        logger.info("==========================================================================================");
        stopLogging();
    }

    public void stopLogging() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.stop();
    }

    public void startLogging() {
        deleteLog();
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.start();
        context.reconfigure();
        logger = LogManager.getLogger(Hook.class);
    }

    public void deleteLog() {
        File logFile = new File("target/logs/rest-assured.log");
        if (logFile.exists()) {
            if (!logFile.delete()) {
                logger.error("Failed to delete log file: " + logFile.getAbsolutePath());
            }
        }
    }
}
