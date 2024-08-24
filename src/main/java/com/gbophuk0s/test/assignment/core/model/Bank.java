package com.gbophuk0s.test.assignment.core.model;

public class Bank extends AbstractDataObject {

    private String name;

    private Double legalEntityCharge;

    private Double individualCharge;

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLegalEntityCharge() {
        return legalEntityCharge;
    }

    public void setLegalEntityCharge(Double legalEntityCharge) {
        this.legalEntityCharge = legalEntityCharge;
    }

    public Double getIndividualCharge() {
        return individualCharge;
    }

    public void setIndividualCharge(Double individualCharge) {
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
