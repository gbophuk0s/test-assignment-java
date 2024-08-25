package com.gbophuk0s.test.assignment.core.commandline;

import java.util.Map;

public interface CommandProcessor {

    default String getParamsTemplate() {
        return "";
    }

    void process(Map<String, String> values);

}
