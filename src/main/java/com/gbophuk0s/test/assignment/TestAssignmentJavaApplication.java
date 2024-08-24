package com.gbophuk0s.test.assignment;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gbophuk0s.test.assignment.core.commandline.CommandlineConfig;
import com.gbophuk0s.test.assignment.core.commandline.CommandlineProcessor;
import com.gbophuk0s.test.assignment.core.commandline.TaskProcessorRegistry;
import com.gbophuk0s.test.assignment.core.commandline.bank.CreateBankTaskProcessor;
import com.gbophuk0s.test.assignment.core.db.Database;

public class TestAssignmentJavaApplication {

    private static final Logger LOGGER = LogManager.getLogger(TestAssignmentJavaApplication.class);

    public static void main(String[] args) {
        CommandlineConfig config = new CommandlineConfig();
        CommandlineProcessor commandlineProcessor = config.commandlineProcessor();

        while (true) {
            Scanner scanner = new Scanner(System.in);

            LOGGER.info("Type a command..");
            String line = scanner.nextLine();

            try {
                commandlineProcessor.process(line);
            }
            catch (Exception e) {
                LOGGER.error("Error: {}", e.getMessage());
            }
        }
    }
}
