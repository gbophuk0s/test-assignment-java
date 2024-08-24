package com.gbophuk0s.test.assignment.core.commandline;

import java.util.HashMap;
import java.util.Map;

public class CommandlineArgsParser {

    public Map<String, String> args = new HashMap<>();

    public Map<String, String> parse(String... args) {
        Map<String, String> result = new HashMap<>();

        for (String arg : args) {
            if (!arg.startsWith("--")) {
                continue;
            }

            String optionText = arg.substring(2);
            int indexOfEqualsSign = optionText.indexOf("=");
            if (indexOfEqualsSign == -1) {
                continue;
            }

            String optionName = optionText.substring(0, indexOfEqualsSign);
            String optionValue = optionText.substring(indexOfEqualsSign + 1);

            result.put(optionName, optionValue);
        }

        return result;
    }

}
