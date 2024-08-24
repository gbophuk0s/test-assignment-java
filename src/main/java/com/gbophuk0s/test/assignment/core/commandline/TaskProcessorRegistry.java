package com.gbophuk0s.test.assignment.core.commandline;

import java.util.HashMap;
import java.util.Map;

import com.gbophuk0s.test.assignment.support.Args;

public class TaskProcessorRegistry {

    private final Map<String, TaskProcessor> registry = new HashMap<>();

    public void register(String command, TaskProcessor parser) {
        Args.checkNotNull(command, "command");
        Args.checkNotNull(parser, "parser");

        registry.put(command, parser);
    }

    public TaskProcessor resolve(String command) {
        TaskProcessor parser = registry.get(command);

        if (parser == null) {
            throw new IllegalArgumentException(String.format("Unknown command: %s", command));
        }

        return parser;
    }

}
