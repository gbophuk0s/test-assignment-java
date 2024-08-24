package com.gbophuk0s.test.assignment.support;

public final class Args {

    public static <T> T checkNotNull(T arg, String argName) {
        if (arg == null) {
            throw new IllegalArgumentException(String.format(
                "Invalid argument: '%s'. Expected: not null. Actual: null",
                argName
            ));
        }

        return arg;
    }

    private Args() {

    }

}
