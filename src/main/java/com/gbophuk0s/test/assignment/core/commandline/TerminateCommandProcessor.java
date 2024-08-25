package com.gbophuk0s.test.assignment.core.commandline;

import java.util.Map;

public class TerminateCommandProcessor extends AbstractCommandProcessor {

    @Override
    public void process(Map<String, String> values) {
        System.exit(0);
    }

}
