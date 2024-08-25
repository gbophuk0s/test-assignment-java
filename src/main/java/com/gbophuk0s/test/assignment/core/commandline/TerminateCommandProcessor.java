package com.gbophuk0s.test.assignment.core.commandline;

import java.util.Map;

public class TerminateCommandProcessor implements CommandProcessor {

    @Override
    public void process(Map<String, String> values) {
        System.exit(0);
    }

}
