package com.gbophuk0s.test.assignment.core.commandline;

import java.util.Map;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpCommandProcessor implements CommandProcessor {

    private static final Logger LOGGER = LogManager.getLogger(HelpCommandProcessor.class);

    private final Supplier<Map<String, CommandProcessor>> processorsProvider;

    public HelpCommandProcessor(Supplier<Map<String, CommandProcessor>> processorsProvider) {
        this.processorsProvider = processorsProvider;
    }

    @Override
    public void process(Map<String, String> values) {
        Map<String, CommandProcessor> processors = processorsProvider.get();

        processors.forEach((key, value) ->
            LOGGER.info("{} {}", key, value.getParamsTemplate())
        );
    }
}
