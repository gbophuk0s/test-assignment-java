package com.gbophuk0s.test.assignment.core.commandline;

import java.util.HashMap;
import java.util.Map;

import com.gbophuk0s.test.assignment.support.Args;

public class CommandProcessorRegistry {

    private final Map<String, CommandProcessor> registry = new HashMap<>();

    public void register(String command, CommandProcessor parser) {
        Args.checkNotNull(command, "command");
        Args.checkNotNull(parser, "parser");

        registry.put(command, parser);
    }

    public CommandProcessor get(String command) {
        CommandProcessor parser = registry.get(command);

        if (parser == null) {
            throw new IllegalArgumentException(String.format("Unknown command: %s", command));
        }

        return parser;
    }

}
