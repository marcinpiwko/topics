package com.siwz.web.model;

public class Subject {
    private String name;
    private String description;
    private int ects;

    public Subject(String name, String description, int ects) {
        this.name = name;
        this.description = description;
        this.ects = ects;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getEcts() {
        return this.ects;
    }
}
