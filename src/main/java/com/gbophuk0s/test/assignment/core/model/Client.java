package com.gbophuk0s.test.assignment.core.model;

public class Client extends AbstractDataObject {

    private String name;

    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {

        LEGAL_ENTITY,

        INDIVIDUAL

    }

    @Override
    public String toString() {
        return "Client{" +
            "id='" + getId() + '\'' +
            ", name=" + name +
            ", type=" + type +
            '}';
    }
}
