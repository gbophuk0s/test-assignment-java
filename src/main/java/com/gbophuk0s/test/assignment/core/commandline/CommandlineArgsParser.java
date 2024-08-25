package com.gbophuk0s.test.assignment.core.commandline;

import java.util.HashMap;
import java.util.Map;

public class CommandlineArgsParser {

    public Map<String, String> args = new HashMap<>();

    public Map<String, String> parse(String... args) {
        Map<String, String> result = new HashMap<>();

        String lastOptionName = null;
        StringBuilder lastOptionExtraValues = new StringBuilder();

        for (String arg : args) {
            if (!arg.startsWith("--")) {

                if (lastOptionName != null) {
                    lastOptionExtraValues.append(" ").append(arg);
                }

                continue;
            }

            if (lastOptionName != null) {
                result.merge(
                    lastOptionName,
                    lastOptionExtraValues.toString(),
                    (s1, s2) -> String.format("%s %s", s1, s2)
                );

                lastOptionName = null;
                lastOptionExtraValues.setLength(0);
            }

            String optionText = arg.substring(2);
            int indexOfEqualsSign = optionText.indexOf("=");
            if (indexOfEqualsSign == -1) {
                continue;
            }

            String optionName = optionText.substring(0, indexOfEqualsSign);
            String optionValue = optionText.substring(indexOfEqualsSign + 1);

            result.put(optionName, optionValue);

            lastOptionName = optionName;
        }

        if (lastOptionName != null) {
            result.merge(
                lastOptionName,
                lastOptionExtraValues.toString(),
                (s1, s2) -> String.format("%s %s", s1, s2)
            );
        }

        return result;
    }

}
