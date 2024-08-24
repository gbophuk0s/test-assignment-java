package com.gbophuk0s.test.assignment.core.commandline;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandlineProcessor {

    private static final Logger LOGGER = LogManager.getLogger(CommandlineProcessor.class);

    private final CommandProcessorRegistry commandProcessorRegistry;
    private final CommandlineArgsParser commandlineArgsParser;

    public CommandlineProcessor(CommandProcessorRegistry commandProcessorRegistry, CommandlineArgsParser commandlineArgsParser) {
        this.commandProcessorRegistry = commandProcessorRegistry;
        this.commandlineArgsParser = commandlineArgsParser;
    }

    public void process(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Command must be specified");
        }

        String[] args = line.split(" ");
        String command = args[0];

        LOGGER.info("Executing command: {}, arguments: {}", command, args);

        CommandProcessor processor = commandProcessorRegistry.resolve(command);

        Map<String, String> arguments = commandlineArgsParser.parse(args);

        processor.process(arguments);
    }
}
