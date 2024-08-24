package com.gbophuk0s.test.assignment.core.model;

import java.util.Objects;

import com.gbophuk0s.test.assignment.support.Args;

public class Currency {

    public static Currency create(String code) {
        ensureSupportedCurrency(code);

        return new Currency(code);
    }

    private static void ensureSupportedCurrency(String code) {
        Args.checkNotNull(code, "code");

        try {
            java.util.Currency.getInstance(code);
        }
        catch (Exception e) {
            throw new IllegalArgumentException(String.format("Unknown currency code: %s", code));
        }
    }

    private final String code;

    private Currency(String code) {
        Args.checkNotNull(code, "code");

        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Currency that = (Currency) o;

        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Currency{" +
            "code='" + code + '\'' +
            '}';
    }

}
