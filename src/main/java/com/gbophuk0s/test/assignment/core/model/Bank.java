package com.gbophuk0s.test.assignment.core.model;

public class Bank extends AbstractDataObject {

    private String name;

    private double legalEntityCharge;

    private double individualCharge;

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLegalEntityCharge() {
        return legalEntityCharge;
    }

    public void setLegalEntityCharge(double legalEntityCharge) {
        this.legalEntityCharge = legalEntityCharge;
    }

    public double getIndividualCharge() {
        return individualCharge;
    }

    public void setIndividualCharge(double individualCharge) {
        this.individualCharge = individualCharge;
    }

    @Override
    public String toString() {
        return "Bank{" +
            "id='" + getId() + '\'' +
            ", name=" + name +
            ", legalEntityCharge=" + legalEntityCharge +
            ", individualCharge=" + individualCharge +
            '}';
    }
}
